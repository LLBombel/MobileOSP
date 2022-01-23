package com.rafalropel.mobileosp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafalropel.mobileosp.adapters.CarsAdapter
import com.rafalropel.mobileosp.dao.CarsDao
import com.rafalropel.mobileosp.databinding.ActivityCarsBinding
import com.rafalropel.mobileosp.entities.CarsEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private lateinit var binding: ActivityCarsBinding

class CarsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCarsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        val carsDao = (application as App).db.carsDao()

        binding.fabAddCar.setOnClickListener {
            showCarsInput()

        }
        binding.btAddCar.setOnClickListener {
            addCar(carsDao)
        }

        binding.btAddCarCancel.setOnClickListener {
            cancelCarsInput()
        }

        lifecycleScope.launch {
            carsDao.fetchALlCars().collect {
                val list = ArrayList(it)
                displayCars(list, carsDao)
            }
        }


        setContentView(binding.root)
    }

    private fun showCarsInput() {
        binding.llCarsInput.visibility = View.VISIBLE
        binding.rvCarsList.visibility = View.GONE
        binding.fabAddCar.visibility = View.GONE
    }

    private fun cancelCarsInput() {
        binding.llCarsInput.visibility = View.GONE
        binding.rvCarsList.visibility = View.VISIBLE
        binding.fabAddCar.visibility = View.VISIBLE
    }

    private fun addCar(carsDao: CarsDao) {
        binding.apply {
            val carName = etCarName.text.toString()
            val carOwnName = etCarOwnName.text.toString()
            val carCodeName = etCarCodeName.text.toString()
            val carNumber = etCarNumber.text.toString()
            val amount = etAmount.text.toString()
            val carAmountOfPeople = etCarAmoutOfPeople.text.toString()
            val carType = etCarType.text.toString()
            val carTechnicalReviewDate = etTechnicalReviewDate.text.toString()
            val carInsuranceDate = etCarInsuranceDate.text.toString()
            val carWeight = etCarWeight.text.toString()
            val carEnginePower = etCarEnginePower.text.toString()
            val carTank = etCarTank.text.toString()
            val carFoam = etCarFoam.text.toString()
            val carChassis = etCarChassis.text.toString()
            val carChassisYear = etCarChassisYear.text.toString()
            val carStorageLocation = etCarStorageLocation.text.toString()

            if (carName.isNotEmpty()) {
                lifecycleScope.launch {
                    carsDao.insert(
                        CarsEntity(
                            carName = carName,
                            carOwnName = carOwnName,
                            codeName = carCodeName,
                            carNumber = carNumber,
                            amount = amount,
                            type = carType,
                            amountOfPeople = carAmountOfPeople,
                            technicalReviewDate = carTechnicalReviewDate,
                            insuranceDate = carInsuranceDate,
                            weight = carWeight,
                            enginePower = carEnginePower,
                            tank = carTank,
                            foam = carFoam,
                            chassis = carChassis,
                            chassisYear = carChassisYear,
                            storageLocation = carStorageLocation
                        )
                    )
                    rvCarsList.visibility = View.VISIBLE
                    llCarsInput.visibility = View.GONE
                    fabAddCar.visibility = View.VISIBLE

                    etCarName.text?.clear()
                    etCarOwnName.text?.clear()
                    etCarCodeName.text?.clear()
                    etCarNumber.text?.clear()
                    etAmount.text?.clear()
                    etCarAmoutOfPeople.text?.clear()
                    etCarType.text?.clear()
                    etTechnicalReviewDate.text?.clear()
                    etCarInsuranceDate.text?.clear()
                    etCarWeight.text?.clear()
                    etCarEnginePower.text?.clear()
                    etCarTank.text?.clear()
                    etCarFoam.text?.clear()
                    etCarChassis.text?.clear()
                    etCarChassisYear.text?.clear()
                    etCarStorageLocation.text?.clear()
                }
            } else {
                Toast.makeText(
                    this@CarsActivity,
                    "Przynajmniej nazwa musi zostać wprowadzona",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


    }

    private fun deleteCar(id: Int, carsDao: CarsDao) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Czy na pewno usunąć samochód?")
        builder.setCancelable(false)
        builder.setPositiveButton("Tak") { dialogInterface, _ ->
            lifecycleScope.launch {
                carsDao.delete(CarsEntity(id))
                Toast.makeText(applicationContext, "Usunięto samochód", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Nie") { dialogInterface, _ ->
            dialogInterface.dismiss()

        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun displayCars(carsList: ArrayList<CarsEntity>, carsDao: CarsDao) {
        if (carsList.isNotEmpty()) {
            val carsAdapter = CarsAdapter(
                carsList
            ) { deleteId ->
                deleteCar(deleteId, carsDao)
            }

            binding.rvCarsList.layoutManager = LinearLayoutManager(this)
            binding.rvCarsList.adapter = carsAdapter
            binding.rvCarsList.visibility = View.VISIBLE
        }
    }
}

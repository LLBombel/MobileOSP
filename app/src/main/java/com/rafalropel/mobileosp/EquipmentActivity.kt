package com.rafalropel.mobileosp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafalropel.mobileosp.adapters.EquipmentAdapter
import com.rafalropel.mobileosp.dao.EquipmentDao
import com.rafalropel.mobileosp.databinding.ActivityEquipmentBinding
import com.rafalropel.mobileosp.databinding.EquipmentEditDialogBinding
import com.rafalropel.mobileosp.entities.EquipmentEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private lateinit var binding: ActivityEquipmentBinding

class EquipmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEquipmentBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val equipmentDao = (application as App).db.equipmentDao()
        binding.fabAddEquipment.setOnClickListener {
            showEquipmentInput()
        }

        binding.btAddEquipment.setOnClickListener {
            addEquipment(equipmentDao)
        }
        binding.btAddEquipmentCancel.setOnClickListener {
            cancelEquipmentInput()
        }

        lifecycleScope.launch {
            equipmentDao.fetchAllEquipment().collect {
                val list = ArrayList(it)
                displayEquipment(list, equipmentDao)
            }
        }


        setContentView(binding.root)
    }


    private fun showEquipmentInput() {
        binding.llEquipmentInput.visibility = View.VISIBLE
        binding.rvEquipmentList.visibility = View.GONE
        binding.fabAddEquipment.visibility = View.GONE

    }

    private fun cancelEquipmentInput() {
        binding.rvEquipmentList.visibility = View.VISIBLE
        binding.llEquipmentInput.visibility = View.GONE
        binding.fabAddEquipment.visibility = View.VISIBLE

    }

    private fun addEquipment(equipmentDao: EquipmentDao) {
        binding.apply {
            val equipmentName = etEquipmentName.text.toString()
            val equipmentOwnName = etEquipmentOwnName.text.toString()
            val equipmentAmount = etEquipmentamount.text.toString()
            val equipmentStorageLocation = etEquipmentStorageLocation.text.toString()
            val equipmentYear = etEquipmentYear.text.toString()
            val equipmentSerialNumber = etEquipmentSerialNumber.text.toString()
            val equipmentInventoryNumber = etEquipmentInventoryNumber.text.toString()
            val equipmentTechnicalReviewDate = etEquipmentTechnicalReviewDate.text.toString()
            val equipmentPower = etEquipmentPower.text.toString()

            if (equipmentName.isNotEmpty()) {
                lifecycleScope.launch {
                    equipmentDao.insert(
                        EquipmentEntity(
                            equipmentName = equipmentName,
                            equipmentOwnName = equipmentOwnName,
                            equipmentAmount = equipmentAmount,
                            equipmentStorageLocation = equipmentStorageLocation,
                            equipmentYear = equipmentYear,
                            equipmentSerialNumber = equipmentSerialNumber,
                            equipmentInventoryNumber = equipmentInventoryNumber,
                            equipmentTechnicalReviewDate = equipmentTechnicalReviewDate,
                            equipmentEnginePower = equipmentPower
                        )
                    )
                    rvEquipmentList.visibility = View.VISIBLE
                    llEquipmentInput.visibility = View.GONE
                    fabAddEquipment.visibility = View.VISIBLE

                    etEquipmentName.text?.clear()
                    etEquipmentOwnName.text?.clear()
                    etEquipmentamount.text?.clear()
                    etEquipmentStorageLocation.text?.clear()
                    etEquipmentYear.text?.clear()
                    etEquipmentSerialNumber.text?.clear()
                    etEquipmentInventoryNumber.text?.clear()
                    etEquipmentTechnicalReviewDate.text?.clear()
                    etEquipmentPower.text?.clear()
                }

            } else {
                Toast.makeText(
                    this@EquipmentActivity,
                    "Przynajmniej nazwa sprzętu musi zostać wprowadzona",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun deleteEquipment(id: Int, equipmentDao: EquipmentDao) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Czy na pewno usunąć sprzęt?")
        builder.setCancelable(false)
        builder.setPositiveButton("Tak") { dialogInterface, _ ->
            lifecycleScope.launch {
                equipmentDao.delete(EquipmentEntity(id))
                Toast.makeText(applicationContext, "Usunięto sprzęt", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Nie") { dialogInterface, _ ->
            dialogInterface.dismiss()

        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun displayEquipment(
        equipmentList: ArrayList<EquipmentEntity>,
        equipmentDao: EquipmentDao
    ) {
        if (equipmentList.isNotEmpty()) {
            val equipmentAdapter = EquipmentAdapter(equipmentList, {
                deleteId ->
                deleteEquipment(deleteId, equipmentDao)
            }, {
                updateId ->
                updateEquipment(updateId, equipmentDao)
            })

            binding.rvEquipmentList.layoutManager = LinearLayoutManager(this)
            binding.rvEquipmentList.adapter = equipmentAdapter
            binding.rvEquipmentList.visibility = View.VISIBLE
        }
    }

    private fun updateEquipment(id: Int, equipmentDao: EquipmentDao) {
        val updateDialog = Dialog(this, R.style.ThemeOverlay_AppCompat)
        updateDialog.setCancelable(false)
        val binding = EquipmentEditDialogBinding.inflate(layoutInflater)
        updateDialog.setContentView(binding.root)


        lifecycleScope.launch {
            equipmentDao.fetchEquipmentById(id).collect {
                binding.etEquipmentName.setText(it.equipmentName)
                binding.etEquipmentOwnName.setText(it.equipmentOwnName)
                binding.etEquipmentAmount.setText(it.equipmentAmount)
                binding.etEquipmentStorageLocation.setText(it.equipmentStorageLocation)
                binding.etEquipmentYear.setText(it.equipmentYear)
                binding.etEquipmentSerialNumber.setText(it.equipmentSerialNumber)
                binding.etEquipmentInventoryNumber.setText(it.equipmentInventoryNumber)
                binding.etEquipmentTechnicalReviewDate.setText(it.equipmentTechnicalReviewDate)
                binding.etEquipmentPower.setText(it.equipmentEnginePower)
            }
        }

            binding.btnEditEquipmentCancel.setOnClickListener {
                updateDialog.dismiss()
            }

            binding.btnEditEquipmentSave.setOnClickListener {
                val equipmentName = binding.etEquipmentName.text.toString()
                val equipmentOwnName = binding.etEquipmentOwnName.text.toString()
                val equipmentAmount = binding.etEquipmentAmount.text.toString()
                val equipmentStorageLocation = binding.etEquipmentStorageLocation.text.toString()
                val equipmentYear = binding.etEquipmentYear.text.toString()
                val equipmentSerialNumber = binding.etEquipmentSerialNumber.text.toString()
                val equipmentInventoryNumber = binding.etEquipmentInventoryNumber.text.toString()
                val equipmentTechnicalReviewDate = binding.etEquipmentTechnicalReviewDate.text.toString()
                val equipmentPower = binding.etEquipmentPower.text.toString()



                lifecycleScope.launch {
                    equipmentDao.update(
                        EquipmentEntity(
                            id,
                            equipmentName,
                            equipmentOwnName,
                            equipmentAmount,
                            equipmentStorageLocation,
                            equipmentYear,
                            equipmentSerialNumber,
                            equipmentInventoryNumber,
                            equipmentTechnicalReviewDate,
                            equipmentPower
                        )
                    )
                    Toast.makeText(applicationContext, "Zaktualizowano sprzęt,", Toast.LENGTH_LONG).show()
                    updateDialog.dismiss()
                }

            }
        updateDialog.show()
        }

    }

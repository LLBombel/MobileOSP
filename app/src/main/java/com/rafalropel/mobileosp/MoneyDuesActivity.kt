package com.rafalropel.mobileosp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafalropel.mobileosp.adapters.DuesAdapter
import com.rafalropel.mobileosp.adapters.EquipmentAdapter
import com.rafalropel.mobileosp.dao.DuesDao
import com.rafalropel.mobileosp.dao.EquipmentDao
import com.rafalropel.mobileosp.databinding.ActivityMoneyDuesBinding
import com.rafalropel.mobileosp.entities.DuesEntity
import com.rafalropel.mobileosp.entities.EquipmentEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


private lateinit var binding: ActivityMoneyDuesBinding

class MoneyDuesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMoneyDuesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val duesDao = (application as App).db.duesDao()
        binding.fabAddDues.setOnClickListener {
            showDuesInput()
        }
        binding.btAddDCancelCancel.setOnClickListener {
            cancelDuesInput()
        }

        binding.btAddDue.setOnClickListener {
            addDue(duesDao)
        }

         lifecycleScope.launch {
             duesDao.fetchAllDues().collect {
                 val list = ArrayList(it)
                 displayDues(list, duesDao)
             }
         }


        setContentView(binding.root)
    }

    private fun showDuesInput() {
        binding.llDuesInput.visibility = View.VISIBLE
    }

    private fun cancelDuesInput() {
        binding.llDuesInput.visibility = View.GONE
        binding.rvDuesList.visibility = View.VISIBLE
    }

    private fun addDue(duesDao: DuesDao) {
        binding.apply {
            val nameSurname = etNameSurname.text.toString()
            val declarationDate = etDeclarationDate.text.toString()
            val moneyDeclared = etMoneyDeclared.text.toString()
            val year = etYear.text.toString()
            val date = etDate.text.toString()
            val moneyAmount = etMoneyAmount.text.toString()

            if (nameSurname.isNotEmpty()) {
                lifecycleScope.launch {
                    duesDao.insert(
                        DuesEntity(
                            duesMemberName = nameSurname,
                            declarationSignDate = declarationDate,
                            declaredMoney = moneyDeclared,
                            duesYear = year,
                            duesDate = date,
                            duesMoney = moneyAmount
                        )
                    )

                    rvDuesList.visibility = View.VISIBLE
                    llDuesInput.visibility = View.GONE

                    etNameSurname.text?.clear()
                    etDeclarationDate.text?.clear()
                    etMoneyDeclared.text?.clear()
                    etYear.text?.clear()
                    etDate.text?.clear()
                    etMoneyAmount.text?.clear()
                }
            } else {
                Toast.makeText(
                    this@MoneyDuesActivity,
                    "Przynajmniej imię i nazwisko musi być wypełnione",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun deleteDue(id: Int, duesDao: DuesDao) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Czy na pewno usunąć pozycję?")
        builder.setCancelable(false)
        builder.setPositiveButton("Tak") { dialogInterface, _ ->
            lifecycleScope.launch {
                duesDao.delete(DuesEntity(id))
                Toast.makeText(applicationContext, "Usunięto pozycję", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Nie") { dialogInterface, _ ->
            dialogInterface.dismiss()

        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun displayDues(
        duesList: ArrayList<DuesEntity>,
        duesDao: DuesDao
    ) {
        if (duesList.isNotEmpty()) {
            val duesAdapter = DuesAdapter(
                duesList
            ) { deleteId ->
                deleteDue(deleteId, duesDao)
            }

            binding.rvDuesList.layoutManager = LinearLayoutManager(this)
            binding.rvDuesList.adapter = duesAdapter
            binding.rvDuesList.visibility = View.VISIBLE
        }
    }
}
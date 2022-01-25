package com.rafalropel.mobileosp

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafalropel.mobileosp.adapters.DuesAdapter
import com.rafalropel.mobileosp.dao.DuesDao
import com.rafalropel.mobileosp.databinding.ActivityMoneyDuesBinding
import com.rafalropel.mobileosp.databinding.DueEditDialogBinding
import com.rafalropel.mobileosp.entities.DuesEntity
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
        binding.rvDuesList.visibility = View.GONE
        binding.fabAddDues.visibility = View.GONE
    }

    private fun cancelDuesInput() {
        binding.llDuesInput.visibility = View.GONE
        binding.rvDuesList.visibility = View.VISIBLE
        binding.fabAddDues.visibility = View.VISIBLE
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
                    fabAddDues.visibility = View.VISIBLE

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
            val duesAdapter = DuesAdapter(duesList, { deleteId ->
                deleteDue(deleteId, duesDao)
            }, { updateId ->
                updateDues(updateId, duesDao)
            })


            binding.rvDuesList.layoutManager = LinearLayoutManager(this)
            binding.rvDuesList.adapter = duesAdapter
            binding.rvDuesList.visibility = View.VISIBLE
        }
    }

    private fun updateDues(id: Int, duesDao: DuesDao) {
        val updateDialog = Dialog(this, R.style.ThemeOverlay_AppCompat)
        updateDialog.setCancelable(false)
        val binding = DueEditDialogBinding.inflate(layoutInflater)
        updateDialog.setContentView(binding.root)

        lifecycleScope.launch {
            duesDao.fetchDuesById(id).collect {

                binding.etMemberName.setText(it.duesMemberName)
                binding.etDeclarationSignDate.setText(it.declarationSignDate)
                binding.etDeclaredMoney.setText(it.declaredMoney)
                binding.etDueYear.setText(it.duesYear)
                binding.etDueDate.setText(it.duesDate)
                binding.etDueMoney.setText(it.duesMoney)
            }
        }


        binding.btnEditDueSave.setOnClickListener {
            val memberName = binding.etMemberName.text.toString()
            val declarationSignDate = binding.etDeclarationSignDate.text.toString()
            val declaredMoney = binding.etDeclaredMoney.text.toString()
            val dueYear = binding.etDueYear.text.toString()
            val dueDate = binding.etDueDate.text.toString()
            val dueMoney = binding.etDueMoney.text.toString()


            lifecycleScope.launch {
                duesDao.update(
                    DuesEntity(
                        id,
                        memberName,
                        declarationSignDate,
                        declaredMoney,
                        dueYear,
                        dueDate,
                        dueMoney
                    )
                )
                Toast.makeText(applicationContext, "Zaktualizowano pozycję", Toast.LENGTH_LONG)
                    .show()
                updateDialog.dismiss()
            }

        }

        binding.btnEditDueCancel.setOnClickListener {
            updateDialog.dismiss()
        }
        updateDialog.show()
    }
}
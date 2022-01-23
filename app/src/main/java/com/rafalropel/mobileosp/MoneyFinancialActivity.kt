package com.rafalropel.mobileosp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafalropel.mobileosp.adapters.FinancesAdapter
import com.rafalropel.mobileosp.dao.FinancesDao
import com.rafalropel.mobileosp.databinding.ActivityMoneyFinancialBinding
import com.rafalropel.mobileosp.entities.FinancesEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


private lateinit var binding: ActivityMoneyFinancialBinding


class MoneyFinancialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMoneyFinancialBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val financesDao = (application as App).db.financesDao()

        binding.fabAddFinances.setOnClickListener {
            showFinancesInput()
        }

        binding.btAddfinancesCancel.setOnClickListener {
            cancelFinancesInput()
        }


        binding.btAddFinances.setOnClickListener {
            addFinances(financesDao)
        }

        lifecycleScope.launch {
            financesDao.fetchAllFinances().collect {
                val list = ArrayList(it)
                displayFinances(list, financesDao)
            }
        }

        setContentView(binding.root)
    }

    private fun showFinancesInput() {
        binding.llFinancesInput.visibility = View.VISIBLE
        binding.rvFinancesList.visibility = View.GONE
        binding.fabAddFinances.visibility = View.GONE
    }

    private fun cancelFinancesInput() {
        binding.llFinancesInput.visibility = View.GONE
        binding.rvFinancesList.visibility = View.VISIBLE
        binding.fabAddFinances.visibility = View.VISIBLE
    }

    private fun addFinances(financesDao: FinancesDao) {
        binding.apply {
            val LP = etLP.text.toString()
            val dateOfSave = etDateOfSave.text.toString()
            val type = etType.text.toString()
            val bankIncome = etBankIncome.text.toString()
            val bankOutcome = etBankOutcome.text.toString()
            val bankStatus = etBankStatus.text.toString()
            val cashIncome = etCashIncome.text.toString()
            val cashOutcome = etCashOutcome.text.toString()
            val cashStatus = etCashStatus.text.toString()
            val normalMembersMoney = etNormalMembersMoney.text.toString()
            val supportMembersMoney = etSupportMembersMoney.text.toString()
            val grant = etGrant.text.toString()
            val bankInterest = etBankInterest.text.toString()
            val materials = etMaterials.text.toString()
            val services = etServices.text.toString()
            val BOP = etBOP.text.toString()
            val bussinessTravel = etBussinessTravel.text.toString()
            val other = etOther.text.toString()
            val comments = etComments.text.toString()

            if (LP.isNotEmpty()) {
                lifecycleScope.launch {
                    financesDao.insert(
                        FinancesEntity(
                            lp = LP,
                            dateOfSave = dateOfSave,
                            financesType = type,
                            bankIncome = bankIncome,
                            bankOutcome = bankOutcome,
                            bankStatus = bankStatus,
                            cashIncome = cashIncome,
                            cashOutcome = cashOutcome,
                            cashStatus = cashStatus,
                            normalMembersMoney = normalMembersMoney,
                            supportMembersMoney = supportMembersMoney,
                            grant = grant,
                            bankInterest = bankInterest,
                            materials = materials,
                            services = services,
                            bop = BOP,
                            bussinesTravel = bussinessTravel,
                            other = other,
                            comments = comments
                        )
                    )
                    rvFinancesList.visibility = View.VISIBLE
                    llFinancesInput.visibility = View.GONE
                    fabAddFinances.visibility = View.VISIBLE

                    etLP.text?.clear()
                    etDateOfSave.text?.clear()
                    etType.text?.clear()
                    etBankIncome.text?.clear()
                    etBankOutcome.text?.clear()
                    etBankStatus.text?.clear()
                    etCashIncome.text?.clear()
                    etCashOutcome.text?.clear()
                    etCashStatus.text?.clear()
                    etNormalMembersMoney.text?.clear()
                    etSupportMembersMoney.text?.clear()
                    etGrant.text?.clear()
                    etBankInterest.text?.clear()
                    etMaterials.text?.clear()
                    etServices.text?.clear()
                    etBOP.text?.clear()
                    etBussinessTravel.text?.clear()
                    etOther.text?.clear()
                    etComments.text?.clear()
                }
            } else {
                Toast.makeText(
                    this@MoneyFinancialActivity,
                    "Przynamniej liczba porządkowa musi zostać wpisana",
                    Toast.LENGTH_LONG
                ).show()
            }

        }

    }

    private fun deleteFinances(id: Int, financesDao: FinancesDao) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Czy na pewno usunąć pozycję?")
        builder.setCancelable(false)
        builder.setPositiveButton("Tak") { dialogInterface, _ ->
            lifecycleScope.launch {
                financesDao.delete(FinancesEntity(id))
                Toast.makeText(applicationContext, "Usunięto pozycję", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Nie") { dialogInterface, _ ->
            dialogInterface.dismiss()

        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    private fun displayFinances(
        financesList: ArrayList<FinancesEntity>,
        financesDao: FinancesDao
    ) {
        if (financesList.isNotEmpty()) {
            val financesAdapter = FinancesAdapter(
                financesList
            ) { deleteId ->
                deleteFinances(deleteId, financesDao)
            }

            binding.rvFinancesList.layoutManager = LinearLayoutManager(this)
            binding.rvFinancesList.adapter = financesAdapter
            binding.rvFinancesList.visibility = View.VISIBLE
        }
    }
}
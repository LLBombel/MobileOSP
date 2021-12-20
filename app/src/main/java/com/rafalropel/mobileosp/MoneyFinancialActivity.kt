package com.rafalropel.mobileosp

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivityMoneyFinancialBinding
import com.rafalropel.mobileosp.databinding.AddFinancesDialogBinding


private lateinit var binding: ActivityMoneyFinancialBinding


class MoneyFinancialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMoneyFinancialBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddFinances.setOnClickListener {
            addFinancesDialog()
        }


        setContentView(binding.root)
    }


    private fun addFinancesDialog() {

        val addFinancesDialog = Dialog(this, R.style.ThemeOverlay_MaterialComponents)
        val binding = AddFinancesDialogBinding.inflate(layoutInflater)
        addFinancesDialog.setContentView(binding.root)
        addFinancesDialog.setCancelable(false)


        binding.btAddFinances.setOnClickListener {

            Toast.makeText(this, "Już prawie", Toast.LENGTH_SHORT).show()
            addFinancesDialog.dismiss()

        }

        binding.btAddfinancesCancel.setOnClickListener {

            addFinancesDialog.dismiss()
        }

        addFinancesDialog.show()

    }

}
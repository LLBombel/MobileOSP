package com.rafalropel.mobileosp

import android.app.Dialog
import android.os.Bundle
import android.view.View
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
            showFinancesInput()
        }


        setContentView(binding.root)
    }

    private fun showFinancesInput(){
        binding.llFinancesInput.visibility = View.VISIBLE
    }


}
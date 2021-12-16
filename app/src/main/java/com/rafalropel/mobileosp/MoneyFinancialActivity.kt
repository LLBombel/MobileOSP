package com.rafalropel.mobileosp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rafalropel.mobileosp.databinding.ActivityMoneyFinancialBinding


private lateinit var binding: ActivityMoneyFinancialBinding


class MoneyFinancialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMoneyFinancialBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddFinances.setOnClickListener {
            Toast.makeText(this, "Już niedługo będzie można dodawać fakturki", Toast.LENGTH_SHORT).show()
        }


        setContentView(binding.root)
    }
}
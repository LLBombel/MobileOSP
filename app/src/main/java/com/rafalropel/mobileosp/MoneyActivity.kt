package com.rafalropel.mobileosp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivityMoneyBinding

private lateinit var binding: ActivityMoneyBinding

class MoneyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMoneyBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)


        binding.btDues.setOnClickListener {
            val intent = Intent(this, MoneyDuesActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.btFinancial.setOnClickListener {
            val intent = Intent(this, MoneyFinancialActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }



        setContentView(binding.root)
    }
}
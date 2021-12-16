package com.rafalropel.mobileosp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rafalropel.mobileosp.databinding.ActivityMoneyDuesBinding


private lateinit var binding: ActivityMoneyDuesBinding

class MoneyDuesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMoneyDuesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddDues.setOnClickListener {
            Toast.makeText(this, "Już niedługo będzie można dodawać składki", Toast.LENGTH_SHORT).show()
        }


        setContentView(binding.root)
    }
}
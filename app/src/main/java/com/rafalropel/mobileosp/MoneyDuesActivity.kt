package com.rafalropel.mobileosp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivityMoneyDuesBinding


private lateinit var binding: ActivityMoneyDuesBinding

class MoneyDuesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMoneyDuesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddDues.setOnClickListener {
            showDuesInput()
        }


        setContentView(binding.root)
    }

    private fun showDuesInput() {
        binding.llDuesInput.visibility = View.VISIBLE
    }

}
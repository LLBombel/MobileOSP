package com.rafalropel.mobileosp

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivityMoneyDuesBinding
import com.rafalropel.mobileosp.databinding.AddDueDialogBinding


private lateinit var binding: ActivityMoneyDuesBinding

class MoneyDuesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMoneyDuesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddDues.setOnClickListener {
            addDueDialog()
        }


        setContentView(binding.root)
    }

    private fun addDueDialog() {

        val addDueDialog = Dialog(this, R.style.ThemeOverlay_MaterialComponents)
        val binding = AddDueDialogBinding.inflate(layoutInflater)
        addDueDialog.setContentView(binding.root)
        addDueDialog.setCancelable(false)

        binding.btAddDue.setOnClickListener {

            Toast.makeText(this, "Już prawie", Toast.LENGTH_SHORT).show()
            addDueDialog.dismiss()

        }

        binding.btAddDCancelCancel.setOnClickListener {

            addDueDialog.dismiss()

        }

        addDueDialog.show()
    }

}
package com.rafalropel.mobileosp

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivityCarsBinding
import com.rafalropel.mobileosp.databinding.AddCarDialogBinding

private lateinit var binding: ActivityCarsBinding

class CarsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCarsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddCar.setOnClickListener{
            addCarDialog()

        }




        setContentView(binding.root)
    }

    private fun addCarDialog(){
        val addCarDialog = Dialog(this, R.style.Theme_AppCompat_Dialog)
        addCarDialog.setCancelable(false)
        val binding = AddCarDialogBinding.inflate(layoutInflater)
        addCarDialog.setContentView(binding.root)

        binding.btAddCar.setOnClickListener {
            Toast.makeText(this, "Już prawie", Toast.LENGTH_SHORT).show()
        }

        binding.btAddCarCancel.setOnClickListener {
            addCarDialog.dismiss()
        }

        addCarDialog.show()
    }
}
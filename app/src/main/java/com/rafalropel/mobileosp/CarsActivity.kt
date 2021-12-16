package com.rafalropel.mobileosp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivityCarsBinding

private lateinit var binding: ActivityCarsBinding

class CarsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCarsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddCar.setOnClickListener{
            Toast.makeText(this, "Już niedługo będzie można dodawać samochody", Toast.LENGTH_SHORT).show()
        }




        setContentView(binding.root)
    }
}
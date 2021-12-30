package com.rafalropel.mobileosp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivityCarsBinding

private lateinit var binding: ActivityCarsBinding

class CarsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCarsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddCar.setOnClickListener {
            showCarsInput()

        }




        setContentView(binding.root)
    }

    private fun showCarsInput() {
        binding.llCarsInput.visibility = View.VISIBLE
    }
}
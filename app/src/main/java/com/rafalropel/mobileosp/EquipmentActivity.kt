package com.rafalropel.mobileosp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivityEquipmentBinding

private lateinit var binding: ActivityEquipmentBinding

class EquipmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEquipmentBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddEquipment.setOnClickListener {
            showEquipmentInput()
        }


        setContentView(binding.root)
    }


    private fun showEquipmentInput() {
        binding.llEquipmentInput.visibility = View.VISIBLE
    }
}
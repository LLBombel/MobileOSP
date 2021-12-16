package com.rafalropel.mobileosp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rafalropel.mobileosp.databinding.ActivityEquipmentBinding

private lateinit var binding: ActivityEquipmentBinding
class EquipmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEquipmentBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddEquipment.setOnClickListener {
            Toast.makeText(this, "Już niedługo będzie można dodawać sprzęt", Toast.LENGTH_SHORT).show()
        }


        setContentView(binding.root)
    }
}
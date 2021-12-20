package com.rafalropel.mobileosp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rafalropel.mobileosp.databinding.ActivityEquipmentBinding
import com.rafalropel.mobileosp.databinding.AddEquipmentDialogBinding

private lateinit var binding: ActivityEquipmentBinding
class EquipmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEquipmentBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddEquipment.setOnClickListener {
            addEquipmentDialog()
        }


        setContentView(binding.root)
    }


    private fun addEquipmentDialog(){
        val addEquipmentDialog = Dialog(this, R.style.ThemeOverlay_MaterialComponents)
        val binding = AddEquipmentDialogBinding.inflate(layoutInflater)
        addEquipmentDialog.setContentView(binding.root)
        addEquipmentDialog.setCancelable(false)


        binding.btAddEquipment.setOnClickListener {
            Toast.makeText(this, "Już prawie", Toast.LENGTH_SHORT).show()
            addEquipmentDialog.dismiss()
        }

        binding.btAddEquipmentCancel.setOnClickListener {
            addEquipmentDialog.dismiss()
        }

        addEquipmentDialog.show()
    }
}
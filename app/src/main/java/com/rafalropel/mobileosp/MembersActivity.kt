package com.rafalropel.mobileosp

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivityMembersBinding
import com.rafalropel.mobileosp.databinding.AddMemberDialogBinding

private lateinit var binding: ActivityMembersBinding

class MembersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMembersBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddMembers.setOnClickListener {
            addMemberDialog()
        }





        setContentView(binding.root)
    }


    private fun addMemberDialog(){
        val addMemberDialog = Dialog(this, R.style.ThemeOverlay_MaterialComponents)
       
        addMemberDialog.setCancelable(false)
        val binding = AddMemberDialogBinding.inflate(layoutInflater)
        addMemberDialog.setContentView(binding.root)
        binding.btAddMember.setOnClickListener {
            Toast.makeText(this, "Już prawie", Toast.LENGTH_SHORT).show()
            addMemberDialog.dismiss()
        }

        binding.btAddMemberCancel.setOnClickListener {
            addMemberDialog.dismiss()
        }
            addMemberDialog.show()
    }
}
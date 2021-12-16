package com.rafalropel.mobileosp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivityMembersBinding

private lateinit var binding: ActivityMembersBinding

class MembersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMembersBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddMembers.setOnClickListener {
            Toast.makeText(this, "Już niedługo będzie można dodawać członków", Toast.LENGTH_SHORT).show()
        }





        setContentView(binding.root)
    }
}
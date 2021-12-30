package com.rafalropel.mobileosp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivityMembersBinding

private lateinit var binding: ActivityMembersBinding

class MembersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMembersBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.fabAddMembers.setOnClickListener {
            showMembersInput()
        }





        setContentView(binding.root)
    }


    private fun showMembersInput() {
        binding.llMembersInput.visibility = View.VISIBLE
    }
}
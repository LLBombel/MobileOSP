package com.rafalropel.mobileosp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivitySplashScreenBinding

private lateinit var binding: ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.ivSplashcreen.alpha = 0f
        binding.ivSplashcreen.animate().setDuration(1500).alpha(1f).withEndAction {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
        setContentView(binding.root)
    }
}
package com.rafalropel.mobileosp


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rafalropel.mobileosp.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private var backPressedTime: Long = 0
lateinit var backToast: Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)



        binding.btMembers.setOnClickListener {
            val intent = Intent(this, MembersActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.btCars.setOnClickListener {
            val intent = Intent(this, CarsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.btEquipment.setOnClickListener {
            val intent = Intent(this, EquipmentActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.btMoney.setOnClickListener {
            val intent = Intent(this, MoneyActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }






        setContentView(binding.root)
    }

    override fun onBackPressed() {
        doubleClickExit()
    }

    @SuppressLint("ShowToast")
    private fun doubleClickExit() {
        backToast = Toast.makeText(this, "Naciśnij ponownie aby wyjść", Toast.LENGTH_SHORT)
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            super.onBackPressed()
            return
        } else {
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    override fun onDestroy() {
        super.onDestroy()


    }

}






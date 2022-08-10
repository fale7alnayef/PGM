package com.example.pgm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.balysv.materialripple.MaterialRippleLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val admin = findViewById<MaterialRippleLayout>(R.id.adminRipple)
        val coach = findViewById<MaterialRippleLayout>(R.id.coachRipple)



        admin.setOnClickListener {
            navigateToAdminLogin()
        }

        coach.setOnClickListener {
            navigateToCoachLogin()
        }
    }

    private fun navigateToAdminLogin() {
        startActivity(Intent(applicationContext, AdminLoginActivity::class.java))
        finish()
    }

    private fun navigateToCoachLogin() {
        startActivity(Intent(applicationContext, CoachLoginActivity::class.java))
        finish()
    }
}
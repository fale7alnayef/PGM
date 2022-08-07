package com.example.pgm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.balysv.materialripple.MaterialRippleLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val admin = findViewById<MaterialRippleLayout>(R.id.adminRipple)



        admin.setOnClickListener {
            navigateToAdminLogin()
        }
    }

    private fun navigateToAdminLogin() {
        startActivity(Intent(applicationContext, AdminLoginActivity::class.java))
        finish()
    }
}
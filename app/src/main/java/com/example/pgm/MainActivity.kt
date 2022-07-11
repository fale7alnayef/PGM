package com.example.pgm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.balysv.materialripple.MaterialRippleLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val admin = findViewById<MaterialRippleLayout>(R.id.adminRipple)



        admin.setOnClickListener {
            navigateToAdmin()
        }
    }

    private fun navigateToAdmin() {
        startActivity(Intent(applicationContext, AdminActivity::class.java))

    }
}
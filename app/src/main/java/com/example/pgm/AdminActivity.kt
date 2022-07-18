package com.example.pgm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.balysv.materialripple.MaterialRippleLayout
import de.hdodenhof.circleimageview.CircleImageView

class AdminActivity : AppCompatActivity() {
    lateinit var adminName: TextView
    lateinit var gymName : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        val coach = findViewById<MaterialRippleLayout>(R.id.coaches)
        val trainee = findViewById<MaterialRippleLayout>(R.id.trainee)
        val admin = findViewById<MaterialRippleLayout>(R.id.admin)




        coach.setOnClickListener {
            navigateToCoaches()
        }
        admin.setOnClickListener {
            navigateToAdminInfo()
        }
        trainee.setOnClickListener {
            navigateToTrainees()
        }

    }

    private fun navigateToCoaches() {
        startActivity(Intent(applicationContext, AdminCoachActivity::class.java))

    }
    private fun navigateToTrainees() {
        startActivity(Intent(applicationContext, AdminTraineeActivity::class.java))

    }
    private fun navigateToAdminInfo() {
        startActivity(Intent(applicationContext, ShowAdminInformation::class.java))

    }

}
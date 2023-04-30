package com.example.pgm

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.balysv.materialripple.MaterialRippleLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val admin = findViewById<MaterialRippleLayout>(R.id.adminRipple)
        val adminCard = findViewById<CardView>(R.id.asadmin)
        val coach = findViewById<MaterialRippleLayout>(R.id.coachRipple)
        val coachCard = findViewById<CardView>(R.id.ascoach)
        val trainee = findViewById<MaterialRippleLayout>(R.id.traineeRipple)
        val traineeCard = findViewById<CardView>(R.id.astrainee)
        val view = findViewById<TextView>(R.id.textView1001)

        Handler(Looper.getMainLooper()).postDelayed({
            view.visibility = View.VISIBLE
            adminCard.visibility = View.VISIBLE
            coachCard.visibility = View.VISIBLE
            traineeCard.visibility = View.VISIBLE
        }, 1000)


        admin.setOnClickListener {
            navigateToAdminLogin()
        }

        coach.setOnClickListener {
            navigateToCoachLogin()
        }
        trainee.setOnClickListener {
            navigateToTraineeLogin()
        }
    }

    private fun navigateToTraineeLogin() {
        startActivity(Intent(applicationContext, TraineeLoginActivity::class.java))
        finish()
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
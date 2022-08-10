package com.example.pgm

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.balysv.materialripple.MaterialRippleLayout

class CoachActivity : AppCompatActivity() {
    lateinit var coachName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coach)

        val coach = findViewById<MaterialRippleLayout>(R.id.coachC)
        val trainees = findViewById<MaterialRippleLayout>(R.id.traineesC)
        val coachName = findViewById<TextView>(R.id.nameC)


        coach.setOnClickListener {
            navigateToCoachInformation()
        }

        trainees.setOnClickListener {
            navigateToTrainees()
        }
    }

    private fun navigateToCoachInformation() {
        startActivity(Intent(applicationContext, ShowCoachCoachInformation::class.java))

    }

    private fun navigateToTrainees() {
        startActivity(Intent(applicationContext, ViewTraineesActivity::class.java))

    }
}
package com.example.pgm

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.balysv.materialripple.MaterialRippleLayout

class TraineeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainee)
        val trainee = findViewById<MaterialRippleLayout>(R.id.traineeT)
        val exercises = findViewById<MaterialRippleLayout>(R.id.ExercisesT)
        val traineeName = findViewById<TextView>(R.id.nameT)
        val s = findViewById<TextView>(R.id.situation)

        traineeName.text = Data.name

        trainee.setOnClickListener {
            navigateToTraineeInformation()
        }

        exercises.setOnClickListener {
            navigateToExercises()
        }
        s.setOnClickListener {
            navigateToDays()
        }
    }

    private fun navigateToTraineeInformation() {
        startActivity(Intent(applicationContext, ShowTraineeTraineeInformation::class.java))
    }

    private fun navigateToExercises() {
        startActivity(Intent(applicationContext, TraineeExercisesActivity::class.java))

    }
    private fun navigateToDays() {
        startActivity(Intent(applicationContext, DaysActivity::class.java))

    }

}
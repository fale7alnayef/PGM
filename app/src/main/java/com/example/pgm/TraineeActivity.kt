package com.example.pgm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.balysv.materialripple.MaterialRippleLayout

class TraineeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainee)
        val trainee = findViewById<MaterialRippleLayout>(R.id.traineeT)
        val exercises = findViewById<MaterialRippleLayout>(R.id.ExercisesT)
        val traineeName = findViewById<TextView>(R.id.nameT)


        trainee.setOnClickListener {
            navigateToTraineeInformation()
        }

        exercises.setOnClickListener {
            navigateToExercises()
        }
    }

    private fun navigateToTraineeInformation() {
        startActivity(Intent(applicationContext, ShowTraineeTraineeInformation::class.java))
    }

    private fun navigateToExercises() {
        startActivity(Intent(applicationContext, TraineeExercisesActivity::class.java))

    }

}
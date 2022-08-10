package com.example.pgm

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.balysv.materialripple.MaterialRippleLayout
import de.hdodenhof.circleimageview.CircleImageView

class ShowCoachTraineeInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_coach_trainee_information)

        val image = findViewById<CircleImageView>(R.id.coachTraineeImage)
        val name = findViewById<TextView>(R.id.coachTraineeName)
        val age = findViewById<TextView>(R.id.coachTraineeAge)
        val height = findViewById<TextView>(R.id.coachTraineeHeight)
        val weight = findViewById<TextView>(R.id.coachTraineeWeight)
        val phone = findViewById<TextView>(R.id.coachTraineePhone)
        val back = findViewById<MaterialRippleLayout>(R.id.coachTraineeBack)
        val exe = findViewById<MaterialRippleLayout>(R.id.exe)

        name.text = intent.extras?.get("name").toString()
        phone.text = intent.extras?.get("phone").toString()
        age.text = intent.extras?.get("age").toString()
        height.text = intent.extras?.get("height").toString()
        weight.text = intent.extras?.get("weight").toString()
        image.setImageResource(intent.extras?.get("image").toString().toInt())


        back.setOnClickListener {


            onBackPressed()


        }
        exe.setOnClickListener {


            navigateToExercises()


        }
    }

    private fun navigateToExercises() {
        startActivity(Intent(applicationContext, ExercisesActivity::class.java))

    }
}
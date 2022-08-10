package com.example.pgm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.balysv.materialripple.MaterialRippleLayout
import de.hdodenhof.circleimageview.CircleImageView

class ShowCoachCoachInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_coach_coach_information)


        val back = findViewById<MaterialRippleLayout>(R.id.bbbbbbb)
        val image = findViewById<CircleImageView>(R.id.coachCoachImage)
        val name = findViewById<TextView>(R.id.CoachName)
        val phone = findViewById<TextView>(R.id.coachPhone)
        val age = findViewById<TextView>(R.id.coachAgeC)
        val speciality = findViewById<TextView>(R.id.CoachSpeciality)
        val contract = findViewById<Button>(R.id.CoachButtonC)



        name.text = intent.extras?.get("name").toString()
        phone.text = intent.extras?.get("phone").toString()
        age.text = intent.extras?.get("age").toString()
        speciality.text = intent.extras?.get("speciality").toString()

        back.setOnClickListener {
            onBackPressed()
        }
        contract.setOnClickListener {

            navigateToContract()

        }

    }

    private fun navigateToContract() {

        startActivity(Intent(this, ShowCoachContract::class.java))

    }

}
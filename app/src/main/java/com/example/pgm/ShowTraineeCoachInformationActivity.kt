package com.example.pgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.balysv.materialripple.MaterialRippleLayout

class ShowTraineeCoachInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_trainee_coach_information)
 val back = findViewById<MaterialRippleLayout>(R.id.goback)
        back.setOnClickListener {

            onBackPressed()

        }
    }
}
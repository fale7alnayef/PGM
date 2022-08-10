package com.example.pgm

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShowCoachContract : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_coach_contract)

        val salary = findViewById<TextView>(R.id.coachCoachSalary)
        val startDate = findViewById<TextView>(R.id.coachCoachStartDate)
        val endDate = findViewById<TextView>(R.id.coachCoachEndDate)
    }
}
package com.example.pgm

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActiveContract : AppCompatActivity() {
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var value: TextView
    private lateinit var name: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_contract)

        startDate = findViewById(R.id.activeStartDateCoachCOntract)
        endDate = findViewById(R.id.activeEndDateCoachCOntract)
        name = findViewById(R.id.activeCoachCOntract)
        value = findViewById(R.id.activeContractValue)

        name.text = intent.extras?.get("name").toString()
        value.text = intent.extras?.get("value").toString()
        startDate.text = intent.extras?.get("startDate").toString()
        endDate.text = intent.extras?.get("endDate").toString()
    }
}
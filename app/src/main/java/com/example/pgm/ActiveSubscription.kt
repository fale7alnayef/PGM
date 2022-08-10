package com.example.pgm

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActiveSubscription : AppCompatActivity() {
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var value: TextView
    private lateinit var name: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_subscription)

        startDate = findViewById(R.id.activeStartDateSubscriber)
        endDate = findViewById(R.id.activeEndDateSubscriber)
        name = findViewById(R.id.activeSubscriber)
        value = findViewById(R.id.activeSubsValue)

        name.text = intent.extras?.get("name").toString()
        value.text = intent.extras?.get("value").toString()
        startDate.text = intent.extras?.get("startDate").toString()
        endDate.text = intent.extras?.get("endDate").toString()
    }
}
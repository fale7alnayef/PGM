package com.example.pgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Subscription : AppCompatActivity() {
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var value: TextView
    private lateinit var name: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscription2)

        startDate = findViewById(R.id.startDateSubscriber)
        endDate = findViewById(R.id.endDateSubscriber)
        name = findViewById(R.id.subscriber)
        value = findViewById(R.id.value)

        name.text = intent.extras?.get("name").toString()
        value.text = intent.extras?.get("value").toString()
        startDate.text = intent.extras?.get("startDate").toString()
        endDate.text = intent.extras?.get("endDate").toString()
    }
}
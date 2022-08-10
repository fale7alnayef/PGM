package com.example.pgm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InactiveSubscriptionActivity : AppCompatActivity() {
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var value: TextView
    private lateinit var name: TextView
    private lateinit var renew: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inactive_subscription)
        startDate = findViewById(R.id.inactiveStartDateSubscriber)
        endDate = findViewById(R.id.inactiveEndDateSubscriber)
        name = findViewById(R.id.inactiveSubscriber)
        value = findViewById(R.id.inactiveSubsValue)
        renew = findViewById(R.id.inactiveSubscriptionButton)

        name.text = intent.extras?.get("name").toString()
        value.text = intent.extras?.get("value").toString()
        startDate.text = intent.extras?.get("startDate").toString()
        endDate.text = intent.extras?.get("endDate").toString()


        renew.setOnClickListener {
            navigateToNewSubscription()
        }
    }

    private fun navigateToNewSubscription() {
        startActivity(Intent(this, NewSubscriptionActivity::class.java))
    }
}
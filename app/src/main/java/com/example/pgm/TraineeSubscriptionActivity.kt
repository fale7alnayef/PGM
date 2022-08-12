package com.example.pgm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class TraineeSubscriptionActivity : AppCompatActivity() {
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var payment: TextView
    private lateinit var privatec: TextView
    private lateinit var fullyPaid: TextView
    private lateinit var coach: TextView
    private lateinit var payments: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainee_subscription)

        startDate = findViewById(R.id.traineesdSubs)
        endDate = findViewById(R.id.traineeendSubs)
        coach = findViewById(R.id.traineeCoachT)
        payment = findViewById(R.id.traineePrice)
        fullyPaid = findViewById(R.id.traineeFullyPaidSubs)
        privatec = findViewById(R.id.traineePrivateSubs)
        payments = findViewById(R.id.traineePayments)

        payments.setOnClickListener {
            navigateToPayment()
        }
    }

    private fun navigateToPayment() {
        startActivity(Intent(applicationContext, PaymentsActivity::class.java))

    }

}
package com.example.pgm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton



class SubscriptionActivity : AppCompatActivity() {

    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var payment: TextView
    private lateinit var privatec: TextView
    private lateinit var fullyPaid: TextView
    private lateinit var coach: TextView
    private lateinit var pay: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subscription)

        startDate = findViewById(R.id.sdSubs)
        endDate = findViewById(R.id.edSubs)
        coach = findViewById(R.id.sCoach)
        payment = findViewById(R.id.sPayment)
        fullyPaid = findViewById(R.id.fullyPaidSubs)
        privatec = findViewById(R.id.privateSubs)
        pay = findViewById(R.id.sPay)

        pay.setOnClickListener {
            navigateToInstallment()
        }

    }
    private fun navigateToInstallment() {
        startActivity(Intent(applicationContext, InstallmentActivity::class.java))

    }


}
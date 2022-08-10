package com.example.pgm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
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

        val id = intent.extras?.get("id").toString()
        val url = "http://${Data.url}:8000/api/admin/show_sub/$id"
        val queue = Volley.newRequestQueue(applicationContext)

        val subReques = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                startDate.text = it.getString("starts_at")
                endDate.text = it.getString("ends_at")
                val private = it.getString("private")
                val fulPaid = it.getString("fully_paid")

                if (private.equals("0")) {
                    privatec.text = "NO"
                } else {
                    privatec.text = "Yes"
                }

                if (fulPaid.equals("0")) {
                    fullyPaid.text = "NO"
                } else {
                    fullyPaid.text = "YES"
                }

                coach.text = it.getString("coach_name")
                payment.text = it.getString("price")
            },
            {
                Log.e("error", it.toString())
            }
        )
        queue.add(subReques)

        pay.setOnClickListener {
            navigateToInstallment()
        }

    }

    private fun navigateToInstallment() {
        startActivity(Intent(applicationContext, InstallmentActivity::class.java))

    }


}
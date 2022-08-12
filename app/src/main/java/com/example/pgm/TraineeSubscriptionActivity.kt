package com.example.pgm

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.button.MaterialButton

class TraineeSubscriptionActivity : AppCompatActivity() {
    lateinit var subID:String
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


        val queue = Volley.newRequestQueue(applicationContext)
        val url = "http://${Data.url}:8000/api/user/show_sub"
        val token = "Bearer " + Data.Token

        val dataRequest = @RequiresApi(Build.VERSION_CODES.O)
        object : JsonObjectRequest(
            Method.POST,
            url,
            null,
            {
                startDate.text = it.getString("starts_at")
                endDate.text = it.getString("ends_at")
                subID=it.getString("id")
                val private = it.getString("private")
                val fulPaid = it.getString("fully_paid")

                if (private.equals("0")) {
                    privatec.text = "NO"
                } else {
                    privatec.text = "Yes"
                }

                if (fulPaid.equals("false")) {
                    fullyPaid.text = "NO"
                } else {
                    fullyPaid.text = "YES"
                }

                coach.text = it.getString("coach_name")
                payment.text = it.getString("price")
            },
            {

            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = token
                return headers
            }
        }
        queue.add(dataRequest)


        payments.setOnClickListener {
            navigateToPayment()
        }
    }

    private fun navigateToPayment() {
        val i = Intent(applicationContext, PaymentsActivity::class.java)
        i.putExtra("sub_id",subID)
        startActivity(i)

    }

}
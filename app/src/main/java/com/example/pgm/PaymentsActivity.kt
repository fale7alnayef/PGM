package com.example.pgm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlin.collections.ArrayList

class PaymentsActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    lateinit var subID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payments)

        subID = intent.extras?.get("sub_id").toString()

        rv = findViewById(R.id.PaymentsRecycler)
        val url = "http://${Data.url}:8000/api/admin/payments/$subID"
        val queue = Volley.newRequestQueue(applicationContext)
        val payment = ArrayList<PaymentData>()

        val subReques = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                val paymentsaa = it.getJSONArray("payments")
                for (i in 0 until paymentsaa.length()) {
                    val amount = paymentsaa.getJSONObject(i).getString("amount")
                    val date = paymentsaa.getJSONObject(i).getString("created_at")
                    payment.add(
                        PaymentData(
                            amount + " SYP",
                            date.toString().substring(0,10)
                        )
                    )
                    rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                    rv.adapter = PaymentsAdapter(this, payment)
                }

            },
            {
                Log.e("error", url)
            }
        )
        queue.add(subReques)







    }
}
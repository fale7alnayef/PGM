package com.example.pgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PaymentsActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payments)

        rv = findViewById(R.id.PaymentsRecycler)

        val payment = ArrayList<PaymentData>()
        payment.add(PaymentData("ghassan", "kl"))
        payment.add(
            PaymentData(
                "ameer",
                "kjhekfjoewihfweio;jfiefjeklfjkelcmdksfmckdjfekfefmeldedefgfffkhjl/neferfghmkgjrijirjeijfeifje"
            )
        )
        payment.add(PaymentData("ahmad", "ds"))
        payment.add(PaymentData("saif", "fssfaf"))
        payment.add(PaymentData("ghassan", "kl"))
        payment.add(PaymentData("ameer", "kjhkhjl"))
        payment.add(PaymentData("ahmad", "ds"))
        payment.add(PaymentData("saif", "fssfaf"))

        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.adapter = PaymentsAdapter(this, payment)

    }
}
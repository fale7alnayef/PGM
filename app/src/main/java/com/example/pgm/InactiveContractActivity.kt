package com.example.pgm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pgm.Data.Companion.id

class InactiveContractActivity : AppCompatActivity() {
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var value: TextView
    private lateinit var name: TextView
    private lateinit var renew: Button
    private lateinit var id:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inactive_contract)

        startDate = findViewById(R.id.inactiveStartDateCoachCOntract)
        endDate = findViewById(R.id.inactiveEndDateCoachCOntract)
        name = findViewById(R.id.inactiveCoachCOntract)
        value = findViewById(R.id.inactiveContractValue)
        renew = findViewById(R.id.inactiveContractButton)

        name.text = intent.extras?.get("name").toString()
        value.text = intent.extras?.get("value").toString()
        startDate.text = intent.extras?.get("startDate").toString()
        endDate.text = intent.extras?.get("endDate").toString()
        id = intent.extras?.get("id").toString()
        renew.setOnClickListener {
            navigateToNewSubscription()
        }
    }

    private fun navigateToNewSubscription() {
        val i = Intent(this, NewContractActivity::class.java)
        i.putExtra("coach_id",id)
        startActivity(i)
    }
}
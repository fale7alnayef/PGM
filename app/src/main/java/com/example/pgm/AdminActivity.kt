package com.example.pgm

import android.content.Context
import android.content.Intent
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.balysv.materialripple.MaterialRippleLayout
import com.example.pgm.Data.Companion.url
import com.squareup.picasso.Picasso

class AdminActivity : AppCompatActivity() {
    lateinit var adminName: TextView
    lateinit var gymName: TextView
    lateinit var adminImage : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        val coach = findViewById<MaterialRippleLayout>(R.id.coaches)
        val trainee = findViewById<MaterialRippleLayout>(R.id.trainee)
        val subscription = findViewById<MaterialRippleLayout>(R.id.subscription)
        val contract = findViewById<MaterialRippleLayout>(R.id.contract)

        adminName = findViewById(R.id.name)
        gymName = findViewById(R.id.gymname)
        adminImage = findViewById(R.id.adminImage)


            if (Data.img != "null") {
                val imgurl = Data.img.substringAfter("images")
                Picasso.get().load("http://${Data.url}:8000/images$imgurl").into(adminImage)
            }else{
                adminImage.setImageResource(R.drawable.logo)
            }

        adminName.text = Data.name
        gymName.text = Data.gymName


        coach.setOnClickListener {
            navigateToCoaches()
        }

        trainee.setOnClickListener {
            navigateToTrainees()
        }
        subscription.setOnClickListener {
            navigateToSubscription()
        }
        contract.setOnClickListener {
            navigateToContract()
        }

    }

    private fun navigateToCoaches() {
        startActivity(Intent(applicationContext, AdminCoachActivity::class.java))

    }

    private fun navigateToTrainees() {
        startActivity(Intent(applicationContext, AdminTraineeActivity::class.java))

    }

    private fun navigateToSubscription() {
        startActivity(Intent(applicationContext, ViewSubscriptionsActivity::class.java))

    }

    private fun navigateToContract() {
        startActivity(Intent(applicationContext, ViewContractsActivity::class.java))

    }
    private fun deleteData() {

        getSharedPreferences("data", Context.MODE_PRIVATE).edit().apply {
            remove("adminToken")
            remove("isAdmin")
            apply()
        }

    }

}
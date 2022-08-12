package com.example.pgm

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.balysv.materialripple.MaterialRippleLayout
import de.hdodenhof.circleimageview.CircleImageView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ShowCoachCoachInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_coach_coach_information)

        val email = findViewById<TextView>(R.id.coachEmail)

        val back = findViewById<MaterialRippleLayout>(R.id.bbbbbbb)
        val image = findViewById<CircleImageView>(R.id.coachCoachImage)
        val name = findViewById<TextView>(R.id.CoachName)
        val phone = findViewById<TextView>(R.id.coachPhone)
        val age = findViewById<TextView>(R.id.coachAgeC)
        val speciality = findViewById<TextView>(R.id.CoachSpeciality)
        val contract = findViewById<Button>(R.id.CoachButtonC)

        val queue = Volley.newRequestQueue(applicationContext)
        val jsonObjectRequest = @RequiresApi(Build.VERSION_CODES.O)
        object : JsonObjectRequest(
            Request.Method.GET,
            "http://${Data.url}:8000/api/coach/show_coach",
            null,
            {
                val firstName = it.getString("first_name")
                val lastName = it.getString("last_name")
                val phoneNumber = it.getString("phone_number")
                val birthday = it.getString("birthday")
                val spec = it.getString("speciality")
                val e = it.getString("email")
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val date = LocalDate.parse(birthday, formatter)
                val agee = (LocalDate.now().compareTo(date)).toString()

                email.text = e
                name.text = firstName + " " + lastName
                phone.text = phoneNumber
                speciality.text = spec
                age.text=agee

            },
            {

            }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer " + Data.Token
                return headers

            }
        }

        queue.add(jsonObjectRequest)

        back.setOnClickListener {
            onBackPressed()
        }

        contract.setOnClickListener {
            navigateToContract()
        }


    }

    private fun navigateToContract() {

        startActivity(Intent(this, ShowCoachContract::class.java))

    }

}
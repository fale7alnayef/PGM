package com.example.pgm

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class ShowCoachContract : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_coach_contract)

        val salary = findViewById<TextView>(R.id.coachCoachSalary)
        val startDate = findViewById<TextView>(R.id.coachCoachStartDate)
        val endDate = findViewById<TextView>(R.id.coachCoachEndDate)

        val queue = Volley.newRequestQueue(applicationContext)

        val getContract = object : JsonObjectRequest(
            Method.POST, "http://${Data.url}:8000/api/coach/show_cont", null,
            {

                salary.text = it.getString("salary")
                startDate.text = it.getString("start_date")
                endDate.text = it.getString("end_date")

            }, {
            }) {

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer " + Data.Token
                return headers

            }

        }
        queue.add(getContract)
    }
}

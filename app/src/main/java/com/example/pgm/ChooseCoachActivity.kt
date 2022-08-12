package com.example.pgm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class ChooseCoachActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var coach: ArrayList<CoachData>
    lateinit var UserID:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_coach)
        rv = findViewById(R.id.chooseCoachRecycler)
        coach = ArrayList()

        val url = "http://${Data.url}:8000/api/admin/coach_available"
        UserID=intent.extras?.get("userID").toString()
        val token = "Bearer " + Data.Token
        val queue = Volley.newRequestQueue(applicationContext)
        val coachRequest = object : JsonObjectRequest(
            Method.POST,
            url,
            null,
            {
                val coachArr = it.getJSONArray("Available_coaches")

                for (i in 0 until coachArr.length()) {
                    val firstName = coachArr.getJSONObject(i).getString("first_name")
                    val lastName = coachArr.getJSONObject(i).getString("last_name")
                    val phoneNumber = coachArr.getJSONObject(i).getString("phone_number")
                    val speciality = coachArr.getJSONObject(i).getString("speciality")
                    val id = coachArr.getJSONObject(i).getString("id")
                    val email = coachArr.getJSONObject(i).getString("email")
                    val birthday = coachArr.getJSONObject(i).getString("birthday")

                    coach.add(
                        CoachData(
                            "$firstName $lastName,",
                            R.drawable.download1,
                            phoneNumber,
                            "",
                            id,
                            email,
                            birthday,
                            speciality,
                            UserID
                        )
                    )
                    rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

                    rv.adapter = ChooseCoachAdapter(this, coach)
                }

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
        queue.add(coachRequest)

    }
}
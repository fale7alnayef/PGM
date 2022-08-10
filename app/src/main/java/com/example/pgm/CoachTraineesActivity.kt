package com.example.pgm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CoachTraineesActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var toolBar: Toolbar
    lateinit var d: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coach_trainees)
        rv = findViewById(R.id.ctrecyclerView)
        val user = ArrayList<TraineeData>()

        d = intent.extras?.getString("ii").toString()
        val queue = Volley.newRequestQueue(applicationContext)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            "http://${Data.url}:8000/api/admin/users_coach/$d",
            null,
            {
                val userarray = it.getJSONArray("users")
                for (i in 0 until userarray.length()) {
                    val priv: String
                    val first_name = userarray.getJSONObject(i).getString("first_name")
                    val last_name = userarray.getJSONObject(i).getString("last_name")
                    val private = userarray.getJSONObject(i).getString("private")
                    val id = userarray.getJSONObject(i).getString("id")

                    if (private.equals("0")) {
                        priv = "Not Private"
                    } else {
                        priv = "Private"
                    }
                    user.add(
                        TraineeData(
                            "$first_name $last_name",
                            priv,
                            R.drawable.download1,
                            "22",
                            "height",
                            "weight",
                            "0935",
                            id
                        )
                    )
                }
                rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                rv.adapter = CoachTraineeAdapter(this, user)
            },
            {

            }
        )
        queue.add(jsonObjectRequest)




    }

}
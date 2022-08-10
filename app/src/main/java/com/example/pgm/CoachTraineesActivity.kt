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
                            "0935"
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

//        user.add(TraineeData("ghassan","kl", R.drawable.download1,"22","1.90","70","0992347584"))
//        user.add(TraineeData("ameer","kjhkhjl", R.drawable.download2,"24","1.30","120","0992334548"))
//        user.add(TraineeData("ahmad","ds", R.drawable.download3,"22","2.10","78","09965467584"))
//        user.add(TraineeData("saif","fssfaf", R.drawable.download4,"22","1.50","60","0997543904"))
//        user.add(TraineeData("ghassan","kl", R.drawable.download1,"22","1.90","70","0992347584"))
//        user.add(TraineeData("ameer","kjhkhjl", R.drawable.download2,"24","1.30","120","0992334548"))
//        user.add(TraineeData("ahmad","ds", R.drawable.download3,"22","2.10","78","09965467584"))
//        user.add(TraineeData("saif","fssfaf", R.drawable.download4,"22","1.50","60","0997543904"))


    }

}
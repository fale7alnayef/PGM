package com.example.pgm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class TraineeExercisesActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainee_exercises)

        rv = findViewById(R.id.traineeExercisesRecycler)

        val queue = Volley.newRequestQueue(applicationContext)
        val url = "http://${Data.url}:8000/api/coach/showexes/${Data.id}"
        val exercise = ArrayList<ExerciseData>()

        val exeRequest = JsonObjectRequest(Request.Method.GET, url, null, {
            try {
                val exeArray = it.getJSONArray("exes")
                for (i in 0 until exeArray.length()) {
                    val title = exeArray.getJSONObject(i).getString("title")
                    val desc = exeArray.getJSONObject(i).getString("desc")
                    val id = exeArray.getJSONObject(i).getString("id")
                    exercise.add(ExerciseData(title, desc, id))

                    rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

                    rv.adapter = ExerciseAdapter(this, exercise)

                }
            } catch (e: Exception) {
                Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        }, {
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG).show()
        })

        queue.add(exeRequest)

        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.adapter = ExerciseAdapter(this, exercise)
    }
}
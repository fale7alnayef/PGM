package com.example.pgm

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject

class AddExerciseActivity : AppCompatActivity() {
    lateinit var userID: String

    private lateinit var titleContainer: TextInputLayout
    private lateinit var descriptionContainer: TextInputLayout

    private lateinit var title: TextInputEditText
    private lateinit var description: TextInputEditText
    private lateinit var submit : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)
        titleContainer = findViewById(R.id.exerciseTitleContainer)
        submit=findViewById(R.id.newExerciseButton)

        title = findViewById(R.id.exerciseTitleEditText)

        descriptionContainer = findViewById(R.id.exerciseDescContainer)

        description = findViewById(R.id.exerciseDescEditText)

        userID = intent.extras?.get("userID").toString()

        submit.setOnClickListener{

            val queue = Volley.newRequestQueue(applicationContext)
            val url = "http://${Data.url}:8000/api/coach/add_exe/$userID"
            val jsonBody = JSONObject()
            jsonBody.put("title", title.text)
            jsonBody.put("desc", description.text)
            val exeRequest = JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonBody,
                {
                    Toast.makeText(applicationContext, "added", Toast.LENGTH_LONG).show()
                    finish()
                },
                {
                    Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG).show()

                }
            )


            queue.add(exeRequest)

        }


    }
}
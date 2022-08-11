package com.example.pgm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddExerciseActivity : AppCompatActivity() {
    private lateinit var titleContainer: TextInputLayout
    private lateinit var descriptionContainer: TextInputLayout

    private lateinit var title: TextInputEditText
    private lateinit var description: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)
        titleContainer = findViewById(R.id.exerciseTitleContainer)

        title = findViewById(R.id.exerciseTitleEditText)

        descriptionContainer = findViewById(R.id.exerciseDescContainer)

        description = findViewById(R.id.exerciseDescEditText)
    }
}
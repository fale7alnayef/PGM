package com.example.pgm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExercisesActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercises)

        rv = findViewById(R.id.exercisesRecycler)
        fab = findViewById(R.id.addexercise)

        val exercise = ArrayList<ExerciseData>()
        exercise.add(ExerciseData("ghassan", "kl"))
        exercise.add(
            ExerciseData(
                "ameer",
                "kjhekfjoewihfweio;jfiefjeklfjkelcmdksfmckdjfekfefmeldedefgfffkhjl/neferfghmkgjrijirjeijfeifje"
            )
        )
        exercise.add(ExerciseData("ahmad", "ds"))
        exercise.add(ExerciseData("saif", "fssfaf"))
        exercise.add(ExerciseData("ghassan", "kl"))
        exercise.add(ExerciseData("ameer", "kjhkhjl"))
        exercise.add(ExerciseData("ahmad", "ds"))
        exercise.add(ExerciseData("saif", "fssfaf"))

        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv.adapter = ExerciseAdapter(this, exercise)

        fab.setOnClickListener {

            navigateToAddExerciseation()
        }
    }

    private fun navigateToAddExerciseation() {
        startActivity(Intent(this, AddExerciseActivity::class.java))

    }
}
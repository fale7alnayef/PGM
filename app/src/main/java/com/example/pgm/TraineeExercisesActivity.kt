package com.example.pgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TraineeExercisesActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainee_exercises)

        rv = findViewById(R.id.traineeExercisesRecycler)

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
    }
}
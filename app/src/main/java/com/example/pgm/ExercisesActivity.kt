package com.example.pgm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


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
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                val deletedExercise: ExerciseData =
                    exercise[viewHolder.adapterPosition]

                // below line is to get the position
                // of the item at that position.
                val position = viewHolder.adapterPosition

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                exercise.removeAt(viewHolder.adapterPosition)

                // below line is to notify our item is removed from adapter.
                  rv.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)

                // below line is to display our snackbar with action.
                Snackbar.make(rv, deletedExercise.title, Snackbar.LENGTH_LONG)
                    .setAction("Undo") { // adding on click listener to our action of snack bar.
                        // below line is to add our item to array list with a position.
                        exercise.add(position, deletedExercise)

                        // below line is to notify item is
                        // added to our adapter class.
                           rv.adapter!!.notifyItemInserted(position)
                    }.show()
            } // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(rv)

        fab.setOnClickListener {

            navigateToAddExercises()
        }
    }

    private fun navigateToAddExercises() {
        startActivity(Intent(this, AddExerciseActivity::class.java))

    }
}
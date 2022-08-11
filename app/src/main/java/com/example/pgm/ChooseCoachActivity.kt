package com.example.pgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class ChooseCoachActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private lateinit var coach: ArrayList<CoachData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_coach)
        rv = findViewById(R.id.chooseCoachRecycler)
        coach = ArrayList()
        coach.add(
            CoachData(
                "ameer",
                "kjhkhjl",
                R.drawable.download2,
                "24",
                "1.30",
                "120",
                "0992334548","1"
            )
        )
        coach.add(
            CoachData(
                "ahmad",
                "ds",
                R.drawable.download3,
                "22",
                "2.10",
                "78",
                "09965467584","1"
            )
        )
        coach.add(
            CoachData(
                "saif",
                "fssfaf",
                R.drawable.download4,
                "22",
                "1.50",
                "60",
                "0997543904","1"
            )
        )

        rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        rv.adapter = ChooseCoachAdapter(this, coach)


    }
}
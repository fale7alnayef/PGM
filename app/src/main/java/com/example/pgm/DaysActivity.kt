package com.example.pgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton

class DaysActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_days)

        val sat = findViewById<RadioButton>(R.id.radioButtonSat)
        val sun = findViewById<RadioButton>(R.id.radioButtonSun)
        val mon = findViewById<RadioButton>(R.id.radioButtonMon)
        val tue = findViewById<RadioButton>(R.id.radioButtonTue)
        val wed = findViewById<RadioButton>(R.id.radioButtonWed)
        val thu = findViewById<RadioButton>(R.id.radioButtonThu)


        sat.isChecked= true
        mon.isChecked= true
        thu.isChecked= true
    }
}
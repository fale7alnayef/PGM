package com.example.pgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ShowDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)
        val t  = findViewById<TextView>(R.id.textView)
        t.text = intent.extras?.get("name").toString()
    }
}
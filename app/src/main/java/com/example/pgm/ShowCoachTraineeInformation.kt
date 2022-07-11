package com.example.pgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.balysv.materialripple.MaterialRippleLayout
import de.hdodenhof.circleimageview.CircleImageView

class ShowCoachTraineeInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_coach_trainee_information)

        val image = findViewById<CircleImageView>(R.id.profile_image)
        val name = findViewById<TextView>(R.id.name)
        val age = findViewById<TextView>(R.id.age)
        val height = findViewById<TextView>(R.id.height)
        val weight = findViewById<TextView>(R.id.weight)
        val type = findViewById<TextView>(R.id.type)
        val phone = findViewById<TextView>(R.id.phone)

        name.text = intent.extras?.get("name").toString()
        phone.text = intent.extras?.get("phone").toString()
        type.text = intent.extras?.get("type").toString()
        age.text = intent.extras?.get("age").toString()
        height.text = intent.extras?.get("height").toString()
        weight.text = intent.extras?.get("weight").toString()
        image.setImageResource(intent.extras?.get("image").toString().toInt())

        val back = findViewById<MaterialRippleLayout>(R.id.coachtraineeBack)
        back.setOnClickListener {


            onBackPressed()


        }
    }
}
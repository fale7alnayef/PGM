package com.example.pgm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.balysv.materialripple.MaterialRippleLayout
import de.hdodenhof.circleimageview.CircleImageView

class ShowTraineeTraineeInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_trainee_trainee_information)
        val image = findViewById<CircleImageView>(R.id.traineeImageTt)
        val name = findViewById<TextView>(R.id.traineeNameT)
        val age = findViewById<TextView>(R.id.traineeAgeT)
        val height = findViewById<TextView>(R.id.traineeHeightT)
        val weight = findViewById<TextView>(R.id.traineeWeightT)
        val phone = findViewById<TextView>(R.id.traineePhoneT)
        val back = findViewById<MaterialRippleLayout>(R.id.traineeBackT)
        val subs = findViewById<MaterialRippleLayout>(R.id.treaineeSubsT)

        name.text = intent.extras?.get("name").toString()
        phone.text = intent.extras?.get("phone").toString()
        age.text = intent.extras?.get("age").toString()
        height.text = intent.extras?.get("height").toString()
        weight.text = intent.extras?.get("weight").toString()
       // image.setImageResource(intent.extras?.get("image").toString().toInt())

        back.setOnClickListener {


            onBackPressed()


        }

        subs.setOnClickListener {


            navigateToSubs()


        }

    }

    private fun navigateToSubs() {
        startActivity(Intent(applicationContext, TraineeSubscriptionActivity::class.java))

    }
}
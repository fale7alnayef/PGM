package com.example.pgm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.TextView
import com.balysv.materialripple.MaterialRippleLayout
import de.hdodenhof.circleimageview.CircleImageView

class ShowTraineeInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_trainee_information)
        val image = findViewById<CircleImageView>(R.id.profile_image)
        val name = findViewById<TextView>(R.id.name)
        val age = findViewById<TextView>(R.id.age)
        val height = findViewById<TextView>(R.id.height)
        val weight = findViewById<TextView>(R.id.weight)
        val type = findViewById<TextView>(R.id.type)
        val phone = findViewById<TextView>(R.id.phone)
        val back = findViewById<MaterialRippleLayout>(R.id.traineeBack)
        val subs = findViewById<MaterialRippleLayout>(R.id.subs)
        val coach = findViewById<MaterialRippleLayout>(R.id.traineeCoach)

        name.text = intent.extras?.get("name").toString()
        phone.text = intent.extras?.get("phone").toString()
        type.text = intent.extras?.get("type").toString()
        age.text = intent.extras?.get("age").toString()
        height.text = intent.extras?.get("height").toString()
        weight.text = intent.extras?.get("weight").toString()
        image.setImageResource(intent.extras?.get("image").toString().toInt())


        val sf = SubscriptionFragment()

        subs.setOnClickListener {
            val trans = supportFragmentManager.beginTransaction()
            trans.replace(R.id.subsframe, sf, "ccc")
            trans.commit()
        }

        coach.setOnClickListener {


            startActivity(Intent(this,ShowTraineeCoachInformationActivity::class.java))

        }

        back.setOnClickListener {


            onBackPressed()


        }
    }
    @SuppressLint("ResourceType")
    override fun onBackPressed(){
        val fragment = supportFragmentManager.findFragmentByTag("ccc")
        if(fragment != null)
        {

            val trans = supportFragmentManager.beginTransaction()
            trans.remove(fragment)
            trans.commit()

        }
        else{
            super.onBackPressed()
        }
    }



    }

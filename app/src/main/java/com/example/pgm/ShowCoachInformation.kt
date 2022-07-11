package com.example.pgm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.balysv.materialripple.MaterialRippleLayout
import de.hdodenhof.circleimageview.CircleImageView


class ShowCoachInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_coach_information)

        val contract = findViewById<MaterialRippleLayout>(R.id.contract)
        val trainees = findViewById<MaterialRippleLayout>(R.id.trainees)
        val back = findViewById<MaterialRippleLayout>(R.id.bbbbb)
        val image = findViewById<CircleImageView>(R.id.profile_image)
        val name = findViewById<TextView>(R.id.name)
        val phone = findViewById<TextView>(R.id.phone)
        val type = findViewById<TextView>(R.id.type)
        val salary = findViewById<TextView>(R.id.salary)

        name.text = intent.extras?.get("name").toString()
        phone.text = intent.extras?.get("phone").toString()
        type.text = intent.extras?.get("type").toString()
        salary.text = intent.extras?.get("salary").toString()
        image.setImageResource(intent.extras?.get("image").toString().toInt())

        back.setOnClickListener {
           onBackPressed()
        }

        trainees.setOnClickListener {
            val i = Intent(this,CoachTraineesActivity::class.java)
            startActivity(i)
        }


        val cf = ContractFragment()

        contract.setOnClickListener {
            val trans = supportFragmentManager.beginTransaction()
            trans.replace(R.id.frame, cf, "fff")
            trans.commit()
        }


    }
    @SuppressLint("ResourceType")
    override fun onBackPressed(){
        val fragment = supportFragmentManager.findFragmentByTag("fff")
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
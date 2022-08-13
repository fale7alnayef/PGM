package com.example.pgm

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.MenuInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.balysv.materialripple.MaterialRippleLayout
import de.hdodenhof.circleimageview.CircleImageView


class ShowTraineeInformation : AppCompatActivity() {
    lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_trainee_information)

        val menu = findViewById<MaterialRippleLayout>(R.id.traineeMenu)
        val image = findViewById<CircleImageView>(R.id.traineeImage)
        val name = findViewById<TextView>(R.id.name)
        val age = findViewById<TextView>(R.id.age)
        val height = findViewById<TextView>(R.id.height)
        val weight = findViewById<TextView>(R.id.weight)
        val phone = findViewById<TextView>(R.id.phone)
        val back = findViewById<MaterialRippleLayout>(R.id.traineeBack)
        val subs = findViewById<MaterialRippleLayout>(R.id.subs)
        val newSubs = findViewById<MaterialRippleLayout>(R.id.newSubscription)


        id = intent.extras?.get("id").toString()
        name.text = intent.extras?.get("name").toString()
        phone.text = intent.extras?.get("phone").toString()
        age.text = intent.extras?.get("age").toString()
        height.text = intent.extras?.get("height").toString()
        weight.text = intent.extras?.get("weight").toString()
        image.setImageResource(intent.extras?.get("image").toString().toInt())



        menu.setOnClickListener {
            popUpMenu(menu)
        }


        back.setOnClickListener {


            onBackPressed()


        }
        newSubs.setOnClickListener {


            navigateToNewSubs()


        }

        subs.setOnClickListener {


            navigateToSubs()


        }
    }


    private fun popUpMenu(view: View) {
        val popup = PopupMenu(this, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.smenu, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit -> {
                    navigateToUpdateTrainee()
                }

            }
            true
        }
        popup.show()
    }



    private fun navigateToUpdateTrainee() {
        val i = Intent(applicationContext, UpdateTraineeActivity::class.java)
        i.putExtra("id",id)
        startActivity(i)

    }

    private fun navigateToNewSubs() {
        val i = Intent(applicationContext, NewSubscriptionActivity::class.java)
        i.putExtra("userID", id)
        startActivity(i)

    }

    private fun navigateToSubs() {
        val i = Intent(applicationContext, SubscriptionActivity::class.java)
        i.putExtra("id", id)
        startActivity(i)
    }
}

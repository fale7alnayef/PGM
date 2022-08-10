package com.example.pgm

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.balysv.materialripple.MaterialRippleLayout
import com.example.pgm.Data.Companion.id
import de.hdodenhof.circleimageview.CircleImageView

class ShowTraineeInformation : AppCompatActivity() {
    lateinit var id:String
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
                R.id.remove -> {
                    showDefaultDialog(this)
                }
            }
            true
        }
        popup.show()
    }

    private fun showDefaultDialog(context: Context) {
        val alertDialog = AlertDialog.Builder(context)

        alertDialog.apply {
            setTitle("ALERT")
            setMessage(" DO YOU WANT TO REMOVE THIS COACH")
            setPositiveButton("CANCEL") { _: DialogInterface?, _: Int ->
                Toast.makeText(context, "CANCELED", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("REMOVE") { _, _ ->
                Toast.makeText(context, "REMOVED", Toast.LENGTH_SHORT).show()
            }

        }.create().show()
    }

    private fun navigateToUpdateTrainee() {
        startActivity(Intent(applicationContext, UpdateTraineeActivity::class.java))

    }

    private fun navigateToNewSubs() {
        startActivity(Intent(applicationContext, NewSubscriptionActivity::class.java))

    }

    private fun navigateToSubs() {
        val i = Intent(applicationContext, SubscriptionActivity::class.java)
        i.putExtra("id",id)
        startActivity(i)
    }
}

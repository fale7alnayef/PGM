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
import de.hdodenhof.circleimageview.CircleImageView


class ShowCoachInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_coach_information)

        val menu = findViewById<MaterialRippleLayout>(R.id.coachMenu)
        val trainees = findViewById<MaterialRippleLayout>(R.id.trainees)
        val back = findViewById<MaterialRippleLayout>(R.id.bbbbb)
        val newContract = findViewById<MaterialRippleLayout>(R.id.addContract)
        val image = findViewById<CircleImageView>(R.id.coachImage)
        val name = findViewById<TextView>(R.id.name)
        val phone = findViewById<TextView>(R.id.phone)
        val salary = findViewById<TextView>(R.id.salary)

        name.text = intent.extras?.get("name").toString()
        phone.text = intent.extras?.get("phone").toString()
        salary.text = intent.extras?.get("salary").toString()
        image.setImageResource(intent.extras?.get("image").toString().toInt())

        back.setOnClickListener {
            onBackPressed()
        }

        trainees.setOnClickListener {
            navigateToTrainees()
        }

        menu.setOnClickListener {
            popUpMenu(menu)
        }

        newContract.setOnClickListener {
            navigateToContract()
        }



    }
    private fun navigateToTrainees() {
        startActivity(Intent(applicationContext, CoachTraineesActivity::class.java))

    }
    private fun navigateToContract() {
        startActivity(Intent(applicationContext, NewContractActivity::class.java))

    }

    private fun navigateToUpdateCoach() {
        startActivity(Intent(applicationContext, UpdateCoachActivity::class.java))

    }


      private  fun popUpMenu(view : View){
            val popup = PopupMenu(this, view)
            val inflater: MenuInflater = popup.menuInflater
            inflater.inflate(R.menu.smenu, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId){
                    R.id.edit-> {
                        navigateToUpdateCoach()
                    }
                    R.id.remove-> {
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

}
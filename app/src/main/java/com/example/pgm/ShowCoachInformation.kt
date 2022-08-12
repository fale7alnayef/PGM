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
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.balysv.materialripple.MaterialRippleLayout
import de.hdodenhof.circleimageview.CircleImageView


class ShowCoachInformation : AppCompatActivity() {
    lateinit var d: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_coach_information)
        val specialty=findViewById<TextView>(R.id.specialty)
        val menu = findViewById<MaterialRippleLayout>(R.id.coachMenu)
        val trainees = findViewById<MaterialRippleLayout>(R.id.trainees)
        val startDate = findViewById<TextView>(R.id.contractStartDate)
        val endDate = findViewById<TextView>(R.id.contractEndDate)
        val back = findViewById<MaterialRippleLayout>(R.id.bbbbb)
        val newContract = findViewById<MaterialRippleLayout>(R.id.addContract)
        val image = findViewById<CircleImageView>(R.id.coachImage)
        val name = findViewById<TextView>(R.id.name)
        val phone = findViewById<TextView>(R.id.phone)
        val salary = findViewById<TextView>(R.id.salary)


        name.text = intent.extras?.get("name").toString()
        phone.text = intent.extras?.get("phone").toString()
        salary.text = intent.extras?.get("salary").toString()
        specialty.text = intent.extras?.get("specialty").toString()
        image.setImageResource(intent.extras?.get("image").toString().toInt())
        d = intent.extras?.get("id").toString()

        val queue = Volley.newRequestQueue(applicationContext)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,
            "http://${Data.url}:8000/api/admin/show_coach/$d",
            null,
            {
                try {
                    salary.text = it.getJSONObject("coach").getString("salary")
                    startDate.text = it.getJSONObject("coach").getString("starts_at")
                    endDate.text = it.getJSONObject("coach").getString("ends_at")
                    specialty.text=it.getJSONObject("coach").getString("speciality")

                } catch (e: Exception) {
                    Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_SHORT).show()
                }
            },
            {

            })
        queue.add(jsonObjectRequest)

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
        val i = Intent(applicationContext, CoachTraineesActivity::class.java)
        i.putExtra("ii", d)
        startActivity(i)

    }

    private fun navigateToContract() {
        val i = Intent(applicationContext, NewContractActivity::class.java)
        i.putExtra("coach_id", d)
        startActivity(i)

    }

    private fun navigateToUpdateCoach() {
        val i=Intent(applicationContext, UpdateCoachActivity::class.java)
        i.putExtra("idd",d)
        startActivity(i)

    }


    private fun popUpMenu(view: View) {
        val popup = PopupMenu(this, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.smenu, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit -> {
                    navigateToUpdateCoach()
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
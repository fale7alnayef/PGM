package com.example.pgm

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.balysv.materialripple.MaterialRippleLayout
import de.hdodenhof.circleimageview.CircleImageView
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

        val queue = Volley.newRequestQueue(applicationContext)
        val url = "http://${Data.url}:8000/api/user/show_data"
        val token = "Bearer " + Data.Token
        val dataRequest = @RequiresApi(Build.VERSION_CODES.O)
        object : JsonObjectRequest(
            Method.POST,
            url,
            null,
            {
                val user = it.getJSONObject("user")
                val firstName = user.getString("first_name")
                val lastName = user.getString("last_name")
                val heightt = user.getString("height")
                val weightt = user.getString("weight")
                val phoneNumber = user.getString("phone_number")
                val birthday = user.getString("birthday")
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

                val date = LocalDate.parse(birthday, formatter)
                val agee = (LocalDate.now().compareTo(date)).toString()

                name.text = "$firstName $lastName"
                height.text = heightt
                weight.text = weightt
                phone.text = phoneNumber
                age.text = agee

            },
            {

            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = token
                return headers
            }
        }
        queue.add(dataRequest)

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
package com.example.pgm

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONObject
import java.io.ByteArrayOutputStream

class UpdateCoachActivity : AppCompatActivity() {
    private lateinit var pickImage: CircleImageView

    private lateinit var submit: Button

    private lateinit var emailContainer: TextInputLayout
    private lateinit var passwordContainer: TextInputLayout
    private lateinit var confirmPasswordContainer: TextInputLayout

    private lateinit var numberContainer: TextInputLayout


    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var confirmPassword: TextInputEditText
    private lateinit var number: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_coach)

        pickImage = findViewById(R.id.updatePickCoachImage)

        submit = findViewById(R.id.updateCoachButton)


        email = findViewById(R.id.updateEmailCoachEditText)
        emailContainer = findViewById(R.id.updateEmailCoachContainer)

        password = findViewById(R.id.updatePasswordCoachEditText)
        passwordContainer = findViewById(R.id.updatePasswordCoachContainer)

        confirmPassword = findViewById(R.id.updateConfirmPasswordCoachEditText)
        confirmPasswordContainer = findViewById(R.id.updateConfirmPasswordCoachContainer)


        number = findViewById(R.id.updatePhoneNumberCoachEditText)
        numberContainer = findViewById(R.id.updatePhoneNumberCoachContainer)

        val queue = Volley.newRequestQueue(applicationContext)
        val d = intent.extras?.get("idd").toString()


        submit.setOnClickListener {

            val bitmap = (pickImage.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            val image = stream.toByteArray()

            val url = "http://${Data.url}:8000/api/admin/edit_coach/$d"

            val addNewCoachRequest = object : VolleyMultipartRequest(Method.POST, url, {
                Toast.makeText(applicationContext, "edited", Toast.LENGTH_SHORT).show()

            }, {
                if (it.networkResponse.statusCode == 401) {
                    Toast.makeText(applicationContext, "validation error", Toast.LENGTH_SHORT)
                        .show()
                }
            }) {

                override fun getByteData(): Map<String, DataPart>? {
                    val photo = HashMap<String, DataPart>()
                    photo["img_url"] = DataPart("coach", image)
                    return photo
                }

                override fun getParams(): MutableMap<String, String>? {
                    val jsonBody = HashMap<String, String>()
                    if (email.text?.isNotEmpty() == true) {
                        jsonBody.put("email", email.text.toString())
                    }

                    if (password.text?.isNotEmpty() == true) {
                        jsonBody.put("password", password.text.toString())
                    }

                    if (password.text?.isNotEmpty() == true) {
                        jsonBody.put("phone_number", number.text.toString())
                    }

                    return jsonBody
                }

            }

            queue.add(addNewCoachRequest)

            val jsonBody = JSONObject()

            try {
                jsonBody.put("email", email.text)
            } catch (e: Exception) {
                Log.e("email", e.toString())
            }

            try {
                jsonBody.put("phone_number", number.text)
            } catch (e: Exception) {
                Log.e("phone_number", e.toString())
            }

            try {
                jsonBody.put("password", password.text)
            } catch (e: Exception) {
                Log.e("password", e.toString())
            }


            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonBody,
                {
                    Toast.makeText(applicationContext, "edited", Toast.LENGTH_SHORT).show()
                    finish()
                },
                {


                }
            )
            queue.add(jsonObjectRequest)
        }

        pickImage.setOnClickListener {
            pickImageFromGallery()
        }


    }


    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                pickImage.setImageURI(data?.data)
            }

        }

}
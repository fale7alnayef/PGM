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
import java.util.*


class UpdateTraineeActivity : AppCompatActivity() {
    private lateinit var calendar: Calendar
    private lateinit var pickImage: CircleImageView


    private lateinit var submit: Button

    private lateinit var emailContainer: TextInputLayout
    private lateinit var passwordContainer: TextInputLayout
    private lateinit var confirmPasswordContainer: TextInputLayout
    private lateinit var numberContainer: TextInputLayout
    private lateinit var weightContainer: TextInputLayout
    private lateinit var heightContainer: TextInputLayout

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var confirmPassword: TextInputEditText
    private lateinit var number: TextInputEditText
    private lateinit var weight: TextInputEditText
    private lateinit var height: TextInputEditText
    lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_trainee)

        id = intent.extras?.get("id").toString()

        pickImage = findViewById(R.id.updatepickTraineeImage)



        submit = findViewById(R.id.updateTraineeButton)


        email = findViewById(R.id.updateemailTraineeEditText)
        emailContainer = findViewById(R.id.updateemailTraineeContainer)

        password = findViewById(R.id.updatepasswordTraineeEditText)
        passwordContainer = findViewById(R.id.updatepasswordTraineeContainer)

        confirmPassword = findViewById(R.id.updateconfirmPasswordTraineeEditText)
        confirmPasswordContainer = findViewById(R.id.updateconfirmPasswordTraineeContainer)



        number = findViewById(R.id.updatephoneNumberTraineeEditText)
        numberContainer = findViewById(R.id.updatephoneNumberTraineeContainer)




        weight = findViewById(R.id.updateweightTraineeEditText)
        weightContainer = findViewById(R.id.updateweightTraineeContainer)

        height = findViewById(R.id.updateheightTraineeEditText)
        heightContainer = findViewById(R.id.updateheightTraineeContainer)

        submit.setOnClickListener {

            val queue = Volley.newRequestQueue(applicationContext)
            val url = "http://${Data.url}:8000/api/admin/edit_user/$id"
            val bitmap = (pickImage.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            val image = stream.toByteArray()

            val jsonBody = JSONObject()

            try {
                jsonBody.put("email", email.text.toString())
            } catch (e: Exception) {
                Log.e("email", e.toString())
            }

            try {
                jsonBody.put("phone_number", number.text.toString())
            } catch (e: Exception) {
                Log.e("phone_number", e.toString())
            }

            try {
                jsonBody.put("password", password.text.toString())
            } catch (e: Exception) {
                Log.e("password", e.toString())
            }

            try {
                jsonBody.put("weight", weight.text.toString())
            } catch (e: Exception) {
                Log.e("weight", e.toString())
            }

            try {
                jsonBody.put("height", height.text.toString())
            } catch (e: Exception) {
                Log.e("height", e.toString())
            }


            val editTraineeRequest = object : VolleyMultipartRequest(
                Method.POST,
                url,
                {
                    Toast.makeText(applicationContext, "edited", Toast.LENGTH_SHORT).show()
                    finish()
                },
                {
                    if (it.networkResponse.statusCode == 401) {
                        Toast.makeText(applicationContext, "validation error", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            ) {

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
                        jsonBody.put("phone_number", password.text.toString())
                    }

                    if (weight.text?.isNotEmpty() == true) {
                        jsonBody.put("weight", weight.text.toString())
                    }
                    if (height.text?.isNotEmpty() == true) {
                        jsonBody.put("height", height.text.toString())
                    }

                    return jsonBody
                }

            }
            queue.add(editTraineeRequest)

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
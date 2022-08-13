package com.example.pgm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONException
import org.json.JSONObject

class TraineeLoginActivity : AppCompatActivity() {
    private lateinit var submit: MaterialButton

    private lateinit var emailContainer: TextInputLayout
    private lateinit var passwordContainer: TextInputLayout

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainee_login)
        submit = findViewById(R.id.traineeLoginButtont)


        email = findViewById(R.id.emailTraineeEditTextt)
        emailContainer = findViewById(R.id.emailTraineeContainert)

        password = findViewById(R.id.passwordTraineeEditTextt)
        passwordContainer = findViewById(R.id.passwordTraineeContainert)



        submit.setOnClickListener {
            val queue = Volley.newRequestQueue(applicationContext)
            val jsonBody = JSONObject()

            try {
                jsonBody.put("email", email.text.toString())
                jsonBody.put("password", password.text.toString())

            } catch (e: JSONException) {
                e.printStackTrace()
            }
            val JsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, "http://${Data.url}:8000/api/login/user", jsonBody,
                {
                    Data.name =
                        it.getJSONObject("data").getJSONObject("user").getString("first_name")
                    Data.last_name =
                        it.getJSONObject("data").getJSONObject("user").getString("last_name")
                    Data.Token = it.getJSONObject("data").getString("token")
                    Data.gymName =
                        it.getJSONObject("data").getJSONObject("user").getJSONObject("gym")
                            .getString("title")
                    Data.id = it.getJSONObject("data").getJSONObject("user").getString("id")

                    submitForm()

                }, {
                    try {
                        Toast.makeText(
                            applicationContext,
                            it.networkResponse.headers?.get("message").toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    } catch (e: Exception) {
                        Toast.makeText(
                            applicationContext,
                            "Internet Connection Error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
            val requestQueue = Volley.newRequestQueue(applicationContext)

            queue.add(JsonObjectRequest)
        }

        validate()


    }

    private fun navigateToTrainee() {
        startActivity(Intent(applicationContext, TraineeActivity::class.java))

    }

    private fun emailFocusListener() {
        email.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                emailContainer.error = validEmail()
            }
        }
    }


    private fun validEmail(): String? {
        val emailText = email.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

    private fun passwordFocusListener() {
        password.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                passwordContainer.error = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = password.text.toString()
        if (passwordText.isEmpty()) {
            return "Enter the Password"
        }

        return null
    }

    private fun submitForm() {
        emailContainer.error = validEmail()
        passwordContainer.error = validPassword()

        val validEmail = emailContainer.error == null
        val validPassword = passwordContainer.error == null

        if (validEmail && validPassword) {
            navigateToTrainee()
            finish()
        }

    }


    private fun validate() {

        emailFocusListener()
        passwordFocusListener()

    }

    private fun saveData(token: String, isTrainee: Boolean) {

        val sP = getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor = sP.edit()
        editor.putString("traineeToken",token)
        editor.putBoolean("isTrainee",isTrainee)
        editor.apply()


    }

    private fun checkData(): Boolean {

        val sP = getSharedPreferences("data", Context.MODE_PRIVATE)

        if((sP.getBoolean("isTrainee",false)) && (sP.getString("traineeToken", null)!=null) )
        {
            return true
        }

        return false
    }

    private fun deleteData() {

        getSharedPreferences("data", Context.MODE_PRIVATE).edit().apply {
            remove("traineeToken")
            remove("isTrainee")
            apply()
        }

    }
}

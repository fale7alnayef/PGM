package com.example.pgm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONException
import org.json.JSONObject


class AdminLoginActivity : AppCompatActivity() {
    private lateinit var submit: MaterialButton

    private lateinit var emailContainer: TextInputLayout
    private lateinit var passwordContainer: TextInputLayout

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)


        submit = findViewById(R.id.adminLoginButton)


        email = findViewById(R.id.emailAdminEditText)
        emailContainer = findViewById(R.id.emailAdminContainer)

        password = findViewById(R.id.passwordAdminEditText)
        passwordContainer = findViewById(R.id.passwordAdminContainer)



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
                Request.Method.POST, "http://192.168.102.61:8000/api/login/admin", jsonBody,
                {

                    Data.Token = it.getJSONObject("data").getString("token")
                    submitForm()
                }, {
                    try {
                        Toast.makeText(
                            applicationContext,
                            it.networkResponse.headers?.get("message").toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }catch (e:Exception){
                        Toast.makeText(applicationContext,"Internet Connection Error",Toast.LENGTH_SHORT).show()
                    }

                })
            val requestQueue = Volley.newRequestQueue(applicationContext);

            queue.add(JsonObjectRequest)
        }


        validate()


    }


    private fun navigateToAdmin() {
        startActivity(Intent(applicationContext, AdminActivity::class.java))

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
            navigateToAdmin()
            finish()
        }

    }


    private fun validate() {

        emailFocusListener()
        passwordFocusListener()

    }


}









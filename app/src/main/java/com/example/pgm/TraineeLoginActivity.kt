package com.example.pgm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import com.android.volley.toolbox.Volley
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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
            submitForm()
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
}

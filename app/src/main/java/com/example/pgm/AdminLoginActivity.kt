package com.example.pgm

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


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
            submitForm()
         }

        validate()
    }





    private fun navigateToAdmin() {
        startActivity(Intent(applicationContext, AdminActivity::class.java))

    }

     private fun emailFocusListener(){
        email.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                emailContainer.error = validEmail()
            }
        }
    }



    private fun validEmail(): String? {
        val emailText = email.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }

    private fun passwordFocusListener() {
        password.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                passwordContainer.error = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = password.text.toString()
        if(passwordText.length < 8)
        {
            return "Minimum 8 Character Password"
        }
        if(!passwordText.matches(".*[A-Z].*".toRegex()))
        {
            return "Must Contain 1 Upper-case Character"
        }
        if(!passwordText.matches(".*[a-z].*".toRegex()))
        {
            return "Must Contain 1 Lower-case Character"
        }
        return null
    }

    private fun submitForm()
    {
        emailContainer.error = validEmail()
        passwordContainer.error = validPassword()

        val validEmail = emailContainer.error == null
        val validPassword = passwordContainer.error == null

        if (validEmail && validPassword) {
            navigateToAdmin()
            finish()
        }

    }


private fun validate()
{

    emailFocusListener()
    passwordFocusListener()

}


}









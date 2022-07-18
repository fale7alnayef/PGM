package com.example.pgm

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

//        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox.Dense"
class AdminLoginActivity : AppCompatActivity() {
    private lateinit var submit: MaterialButton

    private lateinit var emailContainer: TextInputLayout
    private lateinit var passwordContainer: TextInputLayout

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)



        submit = findViewById(R.id.submitButton)




        submit.setOnClickListener { navigateToAdmin() }
    }

    private fun navigateToAdmin() {
        startActivity(Intent(applicationContext, AdminActivity::class.java))

    }


}









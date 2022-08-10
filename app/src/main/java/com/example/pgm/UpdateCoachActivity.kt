package com.example.pgm

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*

class UpdateCoachActivity : AppCompatActivity() {
    private lateinit var calendar: Calendar
    private lateinit var pickImage: CircleImageView

    private lateinit var submit: Button

    private lateinit var emailContainer: TextInputLayout
    private lateinit var passwordContainer: TextInputLayout
    private lateinit var confirmPasswordContainer: TextInputLayout
    private lateinit var birthdayContainer: TextInputLayout
    private lateinit var numberContainer: TextInputLayout
    private lateinit var firstNameContainer: TextInputLayout
    private lateinit var lastNameContainer: TextInputLayout

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var confirmPassword: TextInputEditText
    private lateinit var birthday: TextInputEditText
    private lateinit var number: TextInputEditText
    private lateinit var firstName: TextInputEditText
    private lateinit var lastName: TextInputEditText
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

        birthday = findViewById(R.id.updateBirthdayCoachEditText)
        birthdayContainer = findViewById(R.id.updateBirthdayCoachContainer)

        number = findViewById(R.id.updatePhoneNumberCoachEditText)
        numberContainer = findViewById(R.id.updatePhoneNumberCoachContainer)

        firstName = findViewById(R.id.updateFirstNameCoachEditText)
        firstNameContainer = findViewById(R.id.updateFirstNameCoachContainer)

        lastName = findViewById(R.id.updateLastNameCoachEditText)
        lastNameContainer = findViewById(R.id.updateLastNameCoachContainer)


        pickImage.setOnClickListener {
            pickImageFromGallery()
        }

        initDatePicker()

    }

    private fun initDatePicker() {
        calendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLapel(calendar)

        }
        birthday.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun updateLapel(calendar: Calendar) {

        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
        birthday.setText(sdf.format(calendar.time))
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
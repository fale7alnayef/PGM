package com.example.pgm


import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class UpdateTraineeActivity : AppCompatActivity() {
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
    private lateinit var weightContainer: TextInputLayout
    private lateinit var heightContainer: TextInputLayout

    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var confirmPassword: TextInputEditText
    private lateinit var birthday: TextInputEditText
    private lateinit var number: TextInputEditText
    private lateinit var firstName: TextInputEditText
    private lateinit var lastName: TextInputEditText
    private lateinit var weight: TextInputEditText
    private lateinit var height: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_trainee)

        pickImage = findViewById(R.id.updatepickTraineeImage)



        submit = findViewById(R.id.updateTraineeButton)


        email = findViewById(R.id.updateemailTraineeEditText)
        emailContainer = findViewById(R.id.updateemailTraineeContainer)

        password = findViewById(R.id.updatepasswordTraineeEditText)
        passwordContainer = findViewById(R.id.updatepasswordTraineeContainer)

        confirmPassword = findViewById(R.id.updateconfirmPasswordTraineeEditText)
        confirmPasswordContainer = findViewById(R.id.updateconfirmPasswordTraineeContainer)

        birthday = findViewById(R.id.updatebirthDayTraineeEditText)
        birthdayContainer = findViewById(R.id.updatebirthDayTraineeContainer)

        number = findViewById(R.id.updatephoneNumberTraineeEditText)
        numberContainer = findViewById(R.id.updatephoneNumberTraineeContainer)

        firstName = findViewById(R.id.updatefirstNameTraineeEditText)
        firstNameContainer = findViewById(R.id.updatefirstNameTraineeContainer)

        lastName = findViewById(R.id.updatelastNameTraineeEditText)
        lastNameContainer = findViewById(R.id.updatelastNameTraineeContainer)

        weight = findViewById(R.id.updateweightTraineeEditText)
        weightContainer = findViewById(R.id.updateweightTraineeContainer)

        height = findViewById(R.id.updateheightTraineeEditText)
        heightContainer = findViewById(R.id.updateheightTraineeContainer)

        submit.setOnClickListener {

        }


        pickImage.setOnClickListener {
            pickImageFromGallery()
        }

        initDatePicker()

    }
    private fun initDatePicker(){
        calendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener{ _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
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

        val sdf = SimpleDateFormat("dd-MM-yyyy",Locale.UK)
        birthday.setText(sdf.format(calendar.time))
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            pickImage.setImageURI(data?.data)
        }

    }
}
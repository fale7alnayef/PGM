package com.example.pgm

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
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
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class AddCoachActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_add_coach)

        pickImage = findViewById(R.id.pickCoachImage)

        submit = findViewById(R.id.newCoachButton)


        email = findViewById(R.id.emailCoachEditText)
        emailContainer = findViewById(R.id.emailCoachContainer)

        password = findViewById(R.id.passwordCoachEditText)
        passwordContainer = findViewById(R.id.passwordCoachContainer)

        confirmPassword = findViewById(R.id.confirmPasswordCoachEditText)
        confirmPasswordContainer = findViewById(R.id.confirmPasswordCoachContainer)

        birthday = findViewById(R.id.birthDayCoachEditText)
        birthdayContainer = findViewById(R.id.birthDayCoachContainer)

        number = findViewById(R.id.phoneNumberCoachEditText)
        numberContainer = findViewById(R.id.phoneNumberCoachContainer)

        firstName = findViewById(R.id.firstNameCoachEditText)
        firstNameContainer = findViewById(R.id.firstNameCoachContainer)

        lastName = findViewById(R.id.lastNameCoachEditText)
        lastNameContainer = findViewById(R.id.lastNameCoachContainer)

        submit.setOnClickListener {

            val queue = Volley.newRequestQueue(applicationContext)
            val jsonBody = JSONObject()
            val Token = "Bearer " + Data.Token
            try {
                jsonBody.put("email", email.text.toString())
                jsonBody.put("password", password.text.toString())
                jsonBody.put("phone_number", number.text.toString())
                jsonBody.put("birthday", birthday.text.toString())
                jsonBody.put("first_name", firstName.text.toString())
                jsonBody.put("last_name", lastName.text.toString())
                jsonBody.put("speciality", "hooker")

            } catch (e: JSONException) {
                e.printStackTrace()
            }
            val JsonObjectRequest = object : JsonObjectRequest(
                Request.Method.POST, "http://${Data.url}:8000/api/admin/create_coach", jsonBody,
                {
                    Toast.makeText(applicationContext, "added", Toast.LENGTH_SHORT).show()

                }, {
                    Log.e("error", birthday.text.toString())

                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Authorization", Token)
                    return headers

                }
            }
            val requestQueue = Volley.newRequestQueue(applicationContext)

            queue.add(JsonObjectRequest)

            submitForm()
        }

        pickImage.setOnClickListener {
            pickImageFromGallery()
        }

        initDatePicker()
        validate()
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

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.UK)
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
        if (passwordText.length < 8) {
            return "Minimum 8 Character Password"
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())) {
            return "Must Contain 1 Upper-case Character"
        }
        if (!passwordText.matches(".*[a-z].*".toRegex())) {
            return "Must Contain 1 Lower-case Character"
        }
        return null
    }

    private fun confirmPasswordFocusListener() {
        confirmPassword.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                confirmPasswordContainer.error = validconfirmPassword()
            }
        }
    }

    private fun validconfirmPassword(): String? {
        val confirmPasswordText = password.text.toString()

        if (confirmPasswordText.isEmpty()) {

            return "password is not matching"
        } else if (confirmPasswordText != password.text.toString()) {
            return "password is not matching"

        }
        return null
    }

    private fun birthdayFocusListener() {
        birthday.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                birthdayContainer.error = validbirthday()
            }
        }
    }

    private fun validbirthday(): String? {
        val birthdayText = birthday.text.toString()
        if (birthdayText.isEmpty()) {
            return "enter End Birthday"
        }
        return null
    }

    private fun numberFocusListener() {
        number.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                numberContainer.error = validnumber()
            }
        }
    }

    private fun validnumber(): String? {
        val numberText = number.text.toString()
        if (numberText.isEmpty()) {
            return "Enter number"
        }
        return null

    }

    private fun firstNameFocusListener() {
        firstName.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                firstNameContainer.error = validFirstName()
            }
        }
    }

    private fun lastNameFocusListener() {
        lastName.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                lastNameContainer.error = validLastName()
            }
        }
    }

    private fun validFirstName(): String? {
        val firstNameText = firstName.text.toString()
        if (firstNameText.isEmpty()) {
            return "enter First Name"
        } else if ((!firstNameText.matches(".*[A-Z].*".toRegex())) && (!firstNameText.matches(".*[a-z].*".toRegex()))) {
            return "Only chars"
        }
        return null
    }

    private fun validLastName(): String? {
        val lastNameText = lastName.text.toString()
        if (lastNameText.isEmpty()) {
            return "enter Last Name"
        } else if ((!lastNameText.matches(".*[A-Z].*".toRegex())) && (!lastNameText.matches(".*[a-z].*".toRegex()))) {
            return "Only chars"
        }
        return null
    }

    private fun submitForm() {
        emailContainer.error = validEmail()
        passwordContainer.error = validPassword()
        confirmPasswordContainer.error = validconfirmPassword()
        numberContainer.error = validnumber()
        birthdayContainer.error = validbirthday()
        firstNameContainer.error = validFirstName()
        lastNameContainer.error = validLastName()

        val validEmail = emailContainer.error == null
        val validPassword = passwordContainer.error == null
        val validconfirmPassword = confirmPasswordContainer.error == null
        val validnumber = numberContainer.error == null
        val validbirthday = birthdayContainer.error == null
        val validFirstName = firstNameContainer.error == null
        val validLastName = lastNameContainer.error == null

        if (validEmail && validPassword && validconfirmPassword && validnumber && validbirthday && validFirstName && validLastName) {
            finish()
        }

    }

    private fun validate() {

        emailFocusListener()
        passwordFocusListener()
        confirmPasswordFocusListener()
        birthdayFocusListener()
        numberFocusListener()
        firstNameFocusListener()
        lastNameFocusListener()

    }


}
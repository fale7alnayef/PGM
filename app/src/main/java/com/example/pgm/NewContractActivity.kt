package com.example.pgm

import android.app.DatePickerDialog
import android.os.Bundle
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
import java.text.SimpleDateFormat
import java.util.*

class NewContractActivity : AppCompatActivity() {
    private lateinit var submit: MaterialButton

    private lateinit var startDateContainer: TextInputLayout
    private lateinit var endDateContainer: TextInputLayout
    private lateinit var salaryContainer: TextInputLayout

    private lateinit var startDate: TextInputEditText
    private lateinit var endDate: TextInputEditText
    private lateinit var salary: TextInputEditText

    private lateinit var calendar: Calendar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_contract)

        startDate = findViewById(R.id.startDateContractEditText)
        startDateContainer = findViewById(R.id.startDateContractContainer)

        endDate = findViewById(R.id.endDateContractEditText)
        endDateContainer = findViewById(R.id.endDateContractContainer)

        salary = findViewById(R.id.salaryContractEditText)
        salaryContainer = findViewById(R.id.salaryContractContainer)


        submit = findViewById(R.id.newContractButton)



        submit.setOnClickListener {
            val queue = Volley.newRequestQueue(applicationContext)
            val jsonBody = JSONObject()
            val Token = "Bearer " + Data.Token
            val coachID: String
            try {

                jsonBody.put("start_date", startDate.text.toString())
                jsonBody.put("end_date", endDate.text.toString())
                jsonBody.put("salary", salary.text.toString())


            } catch (e: JSONException) {
                e.printStackTrace()
            }
            val JsonObjectRequest = object : JsonObjectRequest(
                Request.Method.POST, "http://192.168.1.110:8000/api/admin/create_cont", jsonBody,
                {
                    Toast.makeText(applicationContext, "added", Toast.LENGTH_SHORT).show()
                    submitForm()

                }, {


                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Authorization", Token)
                    return headers

                }
            }


        }

        initDatePicker()
        initDatePicker2()
        validate()

    }

    private fun submitForm() {

        startDateContainer.error = validstartDate()
        endDateContainer.error = validendDate()
        salaryContainer.error = validSalary()

        val validstartDate = startDateContainer.error == null
        val validendDate = endDateContainer.error == null
        val validSalary = salaryContainer.error == null

        if (validstartDate && validendDate && validSalary) {
            finish()
        }
    }

    private fun startDateFocusListener() {
        startDate.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                startDateContainer.error = validstartDate()
            }
        }
    }

    private fun validstartDate(): String? {
        val startDateText = startDate.text.toString()
        if (startDateText.isEmpty()) {
            return "enter Start Date"
        }
        return null
    }

    private fun endDateFocusListener() {
        endDate.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                startDateContainer.error = validendDate()
            }
        }
    }

    private fun validendDate(): String? {
        val endDateText = endDate.text.toString()
        if (endDateText.isEmpty()) {
            return "enter End Date"
        }
        return null
    }

    private fun salaryFocusListener() {
        salary.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                salaryContainer.error = validSalary()
            }
        }
    }

    private fun validSalary(): String? {
        val salaryText = salary.text.toString()
        if (salaryText.isEmpty()) {
            return "enter Salary"
        } else if (!salaryText.matches(".*[1-9].*".toRegex())) {
            return "Only Numbers"
        }
        return null
    }

    private fun initDatePicker() {
        calendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLapel(calendar)

        }
        startDate.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    private fun initDatePicker2() {
        calendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLapel2(calendar)

        }
        endDate.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

    }

    private fun updateLapel2(calendar: Calendar) {
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
        endDate.setText(sdf.format(calendar.time))
    }

    private fun updateLapel(calendar: Calendar) {

        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
        startDate.setText(sdf.format(calendar.time))
    }


    private fun validate() {

        salaryFocusListener()
        startDateFocusListener()
        endDateFocusListener()
    }

}
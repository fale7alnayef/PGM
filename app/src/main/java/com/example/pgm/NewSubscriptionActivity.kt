package com.example.pgm

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class NewSubscriptionActivity : AppCompatActivity() {

    private lateinit var submit: MaterialButton
    private lateinit var coach: MaterialButton

    private lateinit var calendar: Calendar


    private lateinit var startDateContainer: TextInputLayout
    private lateinit var endDateContainer: TextInputLayout
    private lateinit var priceContainer: TextInputLayout
    private lateinit var payingContainer: TextInputLayout

    private lateinit var startDate: TextInputEditText
    private lateinit var endDate: TextInputEditText
    private lateinit var price: TextInputEditText
    private lateinit var paidAmount: TextInputEditText
    private lateinit var paying: TextInputEditText

    lateinit var coachID: String
    lateinit var userID: String

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var privateSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_subscription)

        coachID = intent.extras?.get("coachID").toString()
        userID = intent.extras?.get("userID").toString()

        startDate = findViewById(R.id.startDateSubscriptionEditText)
        startDateContainer = findViewById(R.id.startDateSubscriptionContainer)

        endDate = findViewById(R.id.endDateSubscriptionEditText)
        endDateContainer = findViewById(R.id.endDateSubscriptionContainer)

        price = findViewById(R.id.priceSubscriptionEditText)
        priceContainer = findViewById(R.id.priceSubscriptionContainer)



        paying = findViewById(R.id.payingSubscriptionEditText)
        payingContainer = findViewById(R.id.payingSubscriptionContainer)


        submit = findViewById(R.id.newSubscriptionButton)
        coach = findViewById(R.id.chooseCoachButton)




        coach.setOnClickListener {
            navigateToChooseCoach()
        }

        initDatePicker()
        initDatePicker2()
        validate()


        privateSwitch = findViewById(R.id.privateSwitch)


        val sat = findViewById<Chip>(R.id.satChip)
        val sun = findViewById<Chip>(R.id.sunChip)
        val mon = findViewById<Chip>(R.id.monChip)
        val tue = findViewById<Chip>(R.id.tueChip)
        val wed = findViewById<Chip>(R.id.wedChip)
        val thu = findViewById<Chip>(R.id.thuChip)

        var satFlag = false
        var sunFlag = false
        var monFlag = false
        var tueFlag = false
        var wedFlag = false
        var thuFlag = false



        sat.setOnClickListener {
            if (satFlag) {
                sat.setChipBackgroundColorResource(R.color.gray)
                satFlag = false

            } else {
                sat.setChipBackgroundColorResource(R.color.gold)
                satFlag = true


            }
        }

        sun.setOnClickListener {
            if (sunFlag) {

                sun.setChipBackgroundColorResource(R.color.gray)
                sunFlag = false

            } else {
                sun.setChipBackgroundColorResource(R.color.gold)
                sunFlag = true

            }
        }

        mon.setOnClickListener {
            if (monFlag) {

                mon.setChipBackgroundColorResource(R.color.gray)
                monFlag = false

            } else {
                mon.setChipBackgroundColorResource(R.color.gold)
                monFlag = true

            }
        }

        tue.setOnClickListener {
            if (tueFlag) {

                tue.setChipBackgroundColorResource(R.color.gray)
                tueFlag = false

            } else {
                tue.setChipBackgroundColorResource(R.color.gold)
                tueFlag = true

            }
        }

        wed.setOnClickListener {
            if (wedFlag) {

                wed.setChipBackgroundColorResource(R.color.gray)

                wedFlag = false

            } else {
                wed.setChipBackgroundColorResource(R.color.gold)

            }
        }

        thu.setOnClickListener {
            if (thuFlag) {

                thu.setChipBackgroundColorResource(R.color.gray)
                thuFlag = false

            } else {
                thu.setChipBackgroundColorResource(R.color.gold)
                thuFlag = true

            }
        }

        submit.setOnClickListener {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = "http://${Data.url}:8000/api/admin/create_sub"
            val jsonBody = JSONObject()
            try {

                jsonBody.put("user_id", userID)
                jsonBody.put("coach_id", coachID)
                jsonBody.put("starts_at", startDate.text.toString())
                jsonBody.put("ends_at", endDate.text.toString())

                if (privateSwitch.isChecked) {
                    jsonBody.put("private", "1")
                } else {
                    jsonBody.put("private", "0")

                }
                jsonBody.put("paid_amount", paying.text.toString())
                jsonBody.put("price", price.text.toString())

                if (!satFlag) {
                    jsonBody.put("sat", "0")
                } else {
                    jsonBody.put("sat", "1")
                }

                if (!sunFlag) {
                    jsonBody.put("sun", "0")
                } else {
                    jsonBody.put("sun", "1")
                }

                if (!monFlag) {
                    jsonBody.put("mon", "0")
                } else {
                    jsonBody.put("mon", "1")
                }

                if (!tueFlag) {
                    jsonBody.put("tue", "0")
                } else {
                    jsonBody.put("tue", "1")
                }

                if (!thuFlag) {
                    jsonBody.put("thu", "0")
                } else {
                    jsonBody.put("thu", "1")
                }

                if (!wedFlag) {
                    jsonBody.put("wed", "0")
                } else {
                    jsonBody.put("wed", "1")
                }
                jsonBody.put("fri", "0")


            } catch (e: Exception) {
                Log.e("jsonerror", e.toString())
            }

            val addSub = JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonBody,
                {
                    Toast.makeText(applicationContext, "added", Toast.LENGTH_SHORT).show()
                    submitForm()

                },
                {
                    Log.e("networkerror", it.networkResponse.headers?.get("message").toString())
                }
            )
            Log.e("user_id", userID)
            Log.e("coach_id", coachID)

            queue.add(addSub)
        }


    }

    private fun navigateToChooseCoach() {
        val i = Intent(this, ChooseCoachActivity::class.java)
        i.putExtra("userID", userID)
        startActivity(i)
    }


    private fun submitForm() {

        startDateContainer.error = validstartDate()
        endDateContainer.error = validendDate()
        priceContainer.error = validPrice()
        payingContainer.error = validPaying()

        val validstartDate = startDateContainer.error == null
        val validendDate = endDateContainer.error == null
        val validPrice = priceContainer.error == null
        val validPaying = payingContainer.error == null

        if (validstartDate && validendDate && validPrice && validPaying) {
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

    private fun priceFocusListener() {
        price.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                priceContainer.error = validPrice()
            }
        }
    }

    private fun validPrice(): String? {
        val priceText = price.text.toString()
        if (priceText.isEmpty()) {
            return "enter Price"
        } else if (!priceText.matches(".*[1-9].*".toRegex())) {
            return "Only Numbers"
        }
        return null
    }


    private fun payingFocusListener() {
        paying.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                payingContainer.error = validPaying()
            }
        }
    }

    private fun validPaying(): String? {
        val payingText = paying.text.toString()
        if (payingText.isEmpty()) {
            return "enter Paying"
        } else if (!payingText.matches(".*[1-9].*".toRegex())) {
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
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.UK)
        endDate.setText(sdf.format(calendar.time))
    }

    private fun updateLapel(calendar: Calendar) {

        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.UK)
        startDate.setText(sdf.format(calendar.time))
    }


    private fun validate() {

        priceFocusListener()
        payingFocusListener()
        startDateFocusListener()
        endDateFocusListener()
    }
}







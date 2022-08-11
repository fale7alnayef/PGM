package com.example.pgm

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*


class NewSubscriptionActivity : AppCompatActivity() {

    private lateinit var submit: MaterialButton
    private lateinit var coach: MaterialButton

    private lateinit var calendar: Calendar


    private lateinit var startDateContainer: TextInputLayout
    private lateinit var endDateContainer: TextInputLayout
    private lateinit var priceContainer: TextInputLayout
    private lateinit var paidAmountContainer: TextInputLayout
    private lateinit var payingContainer: TextInputLayout

    private lateinit var startDate: TextInputEditText
    private lateinit var endDate: TextInputEditText
    private lateinit var price: TextInputEditText
    private lateinit var paidAmount: TextInputEditText
    private lateinit var paying: TextInputEditText

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var privateSwitch: Switch

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var fullyPaidSwitch: Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_subscription)


        startDate = findViewById(R.id.startDateSubscriptionEditText)
        startDateContainer = findViewById(R.id.startDateSubscriptionContainer)

        endDate = findViewById(R.id.endDateSubscriptionEditText)
        endDateContainer = findViewById(R.id.endDateSubscriptionContainer)

        price = findViewById(R.id.priceSubscriptionEditText)
        priceContainer = findViewById(R.id.priceSubscriptionContainer)

        paidAmount = findViewById(R.id.paidAmountSubscriptionEditText)
        paidAmountContainer = findViewById(R.id.paidAmountSubscriptionContainer)

        paying = findViewById(R.id.payingSubscriptionEditText)
        payingContainer = findViewById(R.id.payingSubscriptionContainer)


        submit = findViewById(R.id.newSubscriptionButton)
        coach = findViewById(R.id.chooseCoachButton)


        submit.setOnClickListener {
            submitForm()
        }

        coach.setOnClickListener {
            navigateToChooseCoach()
        }

        initDatePicker()
        initDatePicker2()
        validate()


        privateSwitch = findViewById(R.id.privateSwitch)
        fullyPaidSwitch = findViewById(R.id.fullyPaidSwitch)
        privateSwitch.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {

                Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        fullyPaidSwitch.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {

                Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()
            }
        }

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
                Toast.makeText(
                    this, satFlag.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                sat.setChipBackgroundColorResource(R.color.teal_200)
                satFlag = true
                Toast.makeText(
                    this, satFlag.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        sun.setOnClickListener {
            if (sunFlag) {

                sun.setChipBackgroundColorResource(R.color.gray)
                sunFlag = false
                Toast.makeText(
                    this, sunFlag.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                sun.setChipBackgroundColorResource(R.color.teal_200)
                sunFlag = true
                Toast.makeText(
                    this, sunFlag.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        mon.setOnClickListener {
            if (monFlag) {

                mon.setChipBackgroundColorResource(R.color.gray)
                monFlag = false
                Toast.makeText(
                    this, monFlag.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                mon.setChipBackgroundColorResource(R.color.teal_200)
                monFlag = true
                Toast.makeText(
                    this, monFlag.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        tue.setOnClickListener {
            if (tueFlag) {

                tue.setChipBackgroundColorResource(R.color.gray)
                tueFlag = false
                Toast.makeText(
                    this, tueFlag.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                tue.setChipBackgroundColorResource(R.color.teal_200)
                tueFlag = true
                Toast.makeText(
                    this, tueFlag.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        wed.setOnClickListener {
            if (wedFlag) {

                wed.setChipBackgroundColorResource(R.color.gray)

                wedFlag = false
                Toast.makeText(
                    this, wedFlag.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                wed.setChipBackgroundColorResource(R.color.teal_200)
                wedFlag = true
                Toast.makeText(
                    this, wedFlag.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        thu.setOnClickListener {
            if (thuFlag) {

                thu.setChipBackgroundColorResource(R.color.gray)
                thuFlag = false
                Toast.makeText(
                    this, thuFlag.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                thu.setChipBackgroundColorResource(R.color.teal_200)
                thuFlag = true
                Toast.makeText(
                    this, thuFlag.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }






    }

    private fun navigateToChooseCoach() {
startActivity(Intent(this,ChooseCoachActivity::class.java))
    }


    private fun submitForm() {

        startDateContainer.error = validstartDate()
        endDateContainer.error = validendDate()
        priceContainer.error = validPrice()
        paidAmountContainer.error = validPaidAmount()
        payingContainer.error = validPaying()

        val validstartDate = startDateContainer.error == null
        val validendDate = endDateContainer.error == null
        val validPrice = priceContainer.error == null
        val validPaidAMount = paidAmountContainer.error == null
        val validPaying = payingContainer.error == null

        if (validstartDate && validendDate && validPrice && validPaidAMount && validPaying) {
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

    private fun paidAmountFocusListener() {
        paidAmount.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                paidAmountContainer.error = validPaidAmount()
            }
        }
    }

    private fun validPaidAmount(): String? {
        val paidAmountText = paidAmount.text.toString()
        if (paidAmountText.isEmpty()) {
            return "enter Paid Amount"
        } else if (!paidAmountText.matches(".*[1-9].*".toRegex())) {
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
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
        endDate.setText(sdf.format(calendar.time))
    }

    private fun updateLapel(calendar: Calendar) {

        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.UK)
        startDate.setText(sdf.format(calendar.time))
    }


    private fun validate() {

        priceFocusListener()
        paidAmountFocusListener()
        payingFocusListener()
        startDateFocusListener()
        endDateFocusListener()
    }
}




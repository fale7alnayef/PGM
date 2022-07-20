package com.example.pgm

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.textfield.TextInputEditText
import de.hdodenhof.circleimageview.CircleImageView
import java.text.SimpleDateFormat
import java.util.*

class AddCoachActivity : AppCompatActivity() {

    private lateinit var calendar: Calendar
    private lateinit var tv: TextInputEditText
    private lateinit var pickImage: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_coach)

        tv = findViewById(R.id.birthDayCoachEditText)
        pickImage = findViewById(R.id.pickCoachImage)


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
        tv.setOnClickListener {
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
        tv.setText(sdf.format(calendar.time))
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
package com.example.pgm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class InstallmentActivity : AppCompatActivity() {

    private lateinit var payingContainer: TextInputLayout
    private lateinit var paying: TextInputEditText
    private lateinit var pay: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_installment)

        payingContainer = findViewById(R.id.installmentAmount)
        paying = findViewById(R.id.installmentAmountEditText)
        pay = findViewById(R.id.pay)


        pay.setOnClickListener {
            submitForm()
        }

        validate()

    }

    private fun submitForm() {

        payingContainer.error = validPaying()


        val validPaying = payingContainer.error == null

        if (validPaying) {
            finish()
        }    }

    private fun payingFocusListener(){
        paying.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                payingContainer.error = validPaying()
            }
        }
    }

    private fun validPaying(): String? {
        val payingText = paying.text.toString()
        if(payingText.isEmpty())
        {
            return "enter Paying"
        }
        else if(!payingText.matches(".*[1-9].*".toRegex()))
        {
            return "Only Numbers"
        }
        return null
    }

    private fun validate() {


        payingFocusListener()

    }
}
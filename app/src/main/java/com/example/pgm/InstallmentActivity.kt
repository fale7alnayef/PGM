package com.example.pgm

import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.pgm.Data.Companion.id

import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject

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

        val queue = Volley.newRequestQueue(applicationContext)
        val url = "http://${Data.url}:8000/api/admin/add_payment"
        val id = intent.extras?.get("idd").toString()

        pay.setOnClickListener {
            val jsonObject = JSONObject()
            jsonObject.put("amount",paying.text)
            jsonObject.put("sub_id",id)
            val addPayment = JsonObjectRequest(
                Request.Method.POST,
                url,
                jsonObject,
                {
                    Toast.makeText(applicationContext,"paid",Toast.LENGTH_SHORT).show()
                },
                {

                }
            )
            queue.add(addPayment)
            submitForm()
        }

        validate()

    }

    private fun submitForm() {

        payingContainer.error = validPaying()


        val validPaying = payingContainer.error == null

        if (validPaying) {
            finish()
        }
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

    private fun validate() {


        payingFocusListener()

    }
}
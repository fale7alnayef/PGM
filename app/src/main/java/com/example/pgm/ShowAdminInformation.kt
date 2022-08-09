package com.example.pgm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.balysv.materialripple.MaterialRippleLayout

class ShowAdminInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_admin_information)
 val back = findViewById<MaterialRippleLayout>(R.id.adminBack)
        back.setOnClickListener {


                    onBackPressed()


        }
    }
}
package com.simats.painfreeyoga

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class HomeScreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)  // Make sure this layout file exists

        val adminButton = findViewById<Button>(R.id.adminlogin)
        val patientButton = findViewById<Button>(R.id.patientlogin)

        adminButton.setOnClickListener {
            val intent = Intent(this, AdminLogin::class.java)
            startActivity(intent)
        }

        patientButton.setOnClickListener {
            val intent = Intent(this, PatientLogin::class.java)
            startActivity(intent)
        }
    }
}

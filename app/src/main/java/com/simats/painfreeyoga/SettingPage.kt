package com.simats.painfreeyoga

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SettingPage : AppCompatActivity() {

    private lateinit var questionButton: Button
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_page) // XML file should be named activity_setting_page.xml

        // Initialize buttons
        questionButton = findViewById(R.id.line2) // This is the "QUESTION" button
        logoutButton = findViewById(R.id.line)    // This is the "LOG OUT" button

        // Navigate to QuestionPage on "QUESTION" button click
        questionButton.setOnClickListener {
            val intent = Intent(this, QuestionPage::class.java)
            startActivity(intent)
        }

        // Navigate to PatientLogin on "LOG OUT" button click
        logoutButton.setOnClickListener {
            val intent = Intent(this, PatientLogin::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish() // Close current activity
        }
    }
}

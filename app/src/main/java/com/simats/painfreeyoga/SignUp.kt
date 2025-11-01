package com.simats.painfreeyoga

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up) // XML file: res/layout/activity_sign_up.xml

        // Initialize views
        usernameEditText = findViewById(R.id.et_username)
        emailEditText = findViewById(R.id.et_email)
        phoneEditText = findViewById(R.id.et_phone)
        passwordEditText = findViewById(R.id.et_password)
        signupButton = findViewById(R.id.btn_signup)

        // Handle button click
        signupButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()
                clearFields()

                // Navigate to HomeScreen
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun clearFields() {
        usernameEditText.text.clear()
        emailEditText.text.clear()
        phoneEditText.text.clear()
        passwordEditText.text.clear()
    }
}

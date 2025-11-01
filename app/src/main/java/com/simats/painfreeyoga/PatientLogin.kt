// PatientLogin.kt
package com.simats.painfreeyoga

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.simats.painfreeyoga.api.RetrofitClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientLogin : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: AppCompatButton
    private lateinit var signupButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_login)

        usernameEditText = findViewById(R.id.editTextText)
        passwordEditText = findViewById(R.id.editTextText1)
        loginButton = findViewById(R.id.button2)
        signupButton = findViewById(R.id.button6)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(username, password)
            }
        }

        signupButton.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }
    }

    private fun loginUser(username: String, password: String) {
        val call = RetrofitClient.instance.login(username, password)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val loginResponse = response.body()!!
                    if (loginResponse.status) {
                        Toast.makeText(this@PatientLogin, "Login successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@PatientLogin, PatientDetails::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@PatientLogin, loginResponse.message, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@PatientLogin, "Unexpected response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@PatientLogin, "Login failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

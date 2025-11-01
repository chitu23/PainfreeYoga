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

class AdminLogin : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        usernameEditText = findViewById(R.id.editTextText)
        passwordEditText = findViewById(R.id.editTextText1)
        loginButton = findViewById(R.id.button2)

        loginButton.setOnClickListener {
            val email = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            RetrofitClient.instance.loginAdmin(email, password)
                .enqueue(object : Callback<AdminLoginResponse> {
                    override fun onResponse(
                        call: Call<AdminLoginResponse>,
                        response: Response<AdminLoginResponse>
                    ) {
                        if (response.isSuccessful && response.body()?.status == true) {
                            val adminName = response.body()?.data?.adminname
                            Toast.makeText(this@AdminLogin, "Welcome $adminName", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@AdminLogin, AdminBottomNav::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@AdminLogin, response.body()?.message ?: "Login failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<AdminLoginResponse>, t: Throwable) {
                        Toast.makeText(this@AdminLogin, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                    }
                })
        }
    }
}

package com.simats.painfreeyoga

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.simats.painfreeyoga.api.RetrofitClient
import com.simats.painfreeyoga.api.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientDetails : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var genderEditText: EditText
    private lateinit var numberEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var diseaseEditText: EditText
    private lateinit var submitButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_details)

        nameEditText = findViewById(R.id.editTextText1)
        ageEditText = findViewById(R.id.editTextText2)
        genderEditText = findViewById(R.id.editTextText3)
        numberEditText = findViewById(R.id.editTextText4)
        emailEditText = findViewById(R.id.editTextText5)
        passwordEditText = findViewById(R.id.editTextText8)
        diseaseEditText = findViewById(R.id.editTextText)
        submitButton = findViewById(R.id.button21)

        submitButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val age = ageEditText.text.toString().trim()
            val gender = genderEditText.text.toString().trim()
            val number = numberEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val disease = diseaseEditText.text.toString().trim()

            if (name.isEmpty() || age.isEmpty() || gender.isEmpty() || number.isEmpty() ||
                email.isEmpty() || password.isEmpty() || disease.isEmpty()
            ) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // API call
                RetrofitClient.instance.PatientDetails(
                    name, age, gender, number, email, password, disease
                ).enqueue(object : Callback<ApiResponse> {
                    override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                        if (response.isSuccessful && response.body()?.status == true) {
                            Toast.makeText(this@PatientDetails, response.body()?.message, Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@PatientDetails, ImageView::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@PatientDetails, "Error: ${response.body()?.message ?: "Unknown error"}", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Toast.makeText(this@PatientDetails, "Failed: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}

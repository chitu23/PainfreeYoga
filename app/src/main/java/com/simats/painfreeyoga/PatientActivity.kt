package com.simats.painfreeyoga

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class PatientActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var genderEditText: EditText
    private lateinit var numberEditText: EditText
    private lateinit var problemEditText: EditText
    private lateinit var videoEditText: EditText
    private lateinit var yogaEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        nameEditText = findViewById(R.id.editTextName)
        ageEditText = findViewById(R.id.editTextAge)
        genderEditText = findViewById(R.id.editTextGender)
        numberEditText = findViewById(R.id.editTextNumber)
        problemEditText = findViewById(R.id.editTextProblem)
        videoEditText = findViewById(R.id.editTextVideo)
        yogaEditText = findViewById(R.id.editTextYoga)

        // Get the Parcelable object from intent
        val patient = intent.getParcelableExtra<Patient>("patient")

        patient?.let {
            nameEditText.setText(it.name)
            ageEditText.setText(it.age.toString())
            genderEditText.setText(it.gender ?: "")
            numberEditText.setText(it.contactNumber ?: "")
            problemEditText.setText(it.problem)
            videoEditText.setText(it.recommendedVideo ?: "")
            yogaEditText.setText(it.suggestedYoga ?: "")
        }

        // 👉 Navigate when user clicks Suggested Yoga field
        yogaEditText.setOnClickListener {
            val intent = Intent(this, Answer::class.java)
            intent.putExtra("yoga", yogaEditText.text.toString())  // Optional: pass yoga
            startActivity(intent)
        }
    }
}

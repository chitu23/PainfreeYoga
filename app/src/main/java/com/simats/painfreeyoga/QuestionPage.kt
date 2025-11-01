package com.simats.painfreeyoga

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.simats.painfreeyoga.api.QuestionResponse
import com.simats.painfreeyoga.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionPage : AppCompatActivity() {

    private lateinit var contraindicationsInput: EditText
    private lateinit var buttonOk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_page)

        contraindicationsInput = findViewById(R.id.editTextContraindications)
        buttonOk = findViewById(R.id.buttonOk)

        // Fetch question from API
        getQuestionFromApi()

        buttonOk.setOnClickListener {
            val inputText = contraindicationsInput.text.toString().trim()
            if (inputText.isNotEmpty()) {
                Toast.makeText(this, "Input: $inputText", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SettingPage::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter something", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getQuestionFromApi() {
        RetrofitClient.instance.getQuestion().enqueue(object : Callback<QuestionResponse> {
            override fun onResponse(
                call: Call<QuestionResponse>,
                response: Response<QuestionResponse>
            ) {
                if (response.isSuccessful && response.body()?.status == true) {
                    val questionText = response.body()?.question ?: ""
                    contraindicationsInput.hint = questionText
                } else {
                    Toast.makeText(this@QuestionPage, "Failed to load question", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<QuestionResponse>, t: Throwable) {
                Toast.makeText(this@QuestionPage, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

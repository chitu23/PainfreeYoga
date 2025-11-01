package com.simats.painfreeyoga

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.simats.painfreeyoga.api.AnswerResponse
import com.simats.painfreeyoga.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Answer : AppCompatActivity() {

    private lateinit var editTextContraindications: EditText
    private lateinit var editTextCobraPose: EditText
    private lateinit var buttonOk: AppCompatButton
    private val email = "student@example.com" // TODO: Replace with actual email from login/session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        // Initialize views
        editTextContraindications = findViewById(R.id.editTextContraindications)
        editTextCobraPose = findViewById(R.id.editTextCobraPose)
        buttonOk = findViewById(R.id.buttonOk)

        buttonOk.setOnClickListener {
            val contraindications = editTextContraindications.text.toString().trim()
            val cobraPose = editTextCobraPose.text.toString().trim()

            if (contraindications.isEmpty() || cobraPose.isEmpty()) {
                Toast.makeText(this, "⚠️ Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                val answerText = """
                    Contraindications:
                    $contraindications

                    Cobra Pose:
                    $cobraPose
                """.trimIndent()

                submitAnswer(email, answerText)
            }
        }
    }

    private fun submitAnswer(email: String, answer: String) {
        RetrofitClient.instance.submitAnswer(email, answer)
            .enqueue(object : Callback<AnswerResponse> {
                override fun onResponse(
                    call: Call<AnswerResponse>,
                    response: Response<AnswerResponse>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body?.status == true) {
                            Toast.makeText(this@Answer, "✅ Answer submitted!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@Answer, AdminBottomNav::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this@Answer,
                                "❌ Submission failed: ${body?.message ?: "Unknown error"}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@Answer,
                            "❌ Server error: ${response.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                    Toast.makeText(this@Answer, "⚠️ Network error: ${t.localizedMessage}", Toast.LENGTH_LONG).show()
                }
            })
    }
}

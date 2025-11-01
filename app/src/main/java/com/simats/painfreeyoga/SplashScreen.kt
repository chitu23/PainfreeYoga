package com.simats.painfreeyoga

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    private lateinit var logoImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        logoImage = findViewById(R.id.image)

        // Navigate to HomeScreen after 5 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }, 3000) // changed from 3000 to 5000
    }
}

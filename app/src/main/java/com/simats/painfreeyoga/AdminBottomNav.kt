package com.simats.painfreeyoga

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment

class AdminBottomNav : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_bottom_nav)

        val navView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Show HomeFragment by default
        loadFragment(AdminHomeFragment())

        // Set listener
        navView.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.home -> AdminHomeFragment()
                R.id.profile -> AdminProfileFragment()
                else -> return@setOnItemSelectedListener false
            }
            loadFragment(selectedFragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

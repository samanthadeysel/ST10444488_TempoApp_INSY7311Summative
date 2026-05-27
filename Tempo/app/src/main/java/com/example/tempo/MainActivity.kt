package com.example.tempo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_nav)

        // Start on LoginFragment with bottom nav hidden
        if (savedInstanceState == null) {
            navigateTo(LoginFragment(), showBottomNav = false)
        }

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    navigateTo(HomeFragment(), showBottomNav = true)
                    true
                }
                R.id.nav_add -> {
                    navigateTo(AddBlockFragment(), showBottomNav = true)
                    true
                }
                R.id.nav_schedule -> {
                    navigateTo(ScheduleFragment(), showBottomNav = true)
                    true
                }
                R.id.nav_settings -> {
                    navigateTo(SettingsFragment(), showBottomNav = true)
                    true
                }
                else -> false
            }
        }
    }

    fun navigateTo(fragment: Fragment, showBottomNav: Boolean = false) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()

        bottomNav.visibility = if (showBottomNav) View.VISIBLE else View.GONE
    }
}
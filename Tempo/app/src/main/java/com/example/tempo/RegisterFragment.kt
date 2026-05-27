package com.example.tempo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView

class RegisterFragment : Fragment(R.layout.fragment_register) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity = activity as MainActivity

        view.findViewById<Button>(R.id.btnSignup).setOnClickListener {
            // After successful registration, go to Home and show bottom nav
            mainActivity.navigateTo(HomeFragment(), showBottomNav = true)
        }

        view.findViewById<TextView>(R.id.tvLoginLink).setOnClickListener {
            // Back to login, hide bottom nav
            mainActivity.navigateTo(LoginFragment(), showBottomNav = false)
        }
    }
}
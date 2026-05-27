package com.example.tempo

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class LoginFragment : Fragment(R.layout.fragment_login) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity

        view.findViewById<Button>(R.id.btnLogin).setOnClickListener {
            mainActivity.navigateTo(HomeFragment(), showBottomNav = true)
        }
        view.findViewById<Button>(R.id.tvSignupLink).setOnClickListener {
            mainActivity.navigateTo(RegisterFragment(), showBottomNav = false)
        }
        view.findViewById<Button>(R.id.tvForgotPassword).setOnClickListener {
            mainActivity.navigateTo(ForgotPasswordFragment(), showBottomNav = false)
        }
    }
}

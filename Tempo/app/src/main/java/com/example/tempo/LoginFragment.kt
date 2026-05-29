package com.example.tempo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    private lateinit var btnLogin: Button
    private lateinit var tvForgotPassword: TextView
    private lateinit var tvSignupLink: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Bind views
        btnLogin = view.findViewById(R.id.btnLogin)
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword)
        tvSignupLink = view.findViewById(R.id.tvSignupLink)

        // Buttons
        btnLogin.setOnClickListener {
            (activity as MainActivity).navigateTo(HomeFragment(), showBottomNav = true)
        }

        tvForgotPassword.setOnClickListener {
            (activity as MainActivity).navigateTo(ForgotPasswordFragment(), showBottomNav = false)
        }

        tvSignupLink.setOnClickListener {
            (activity as MainActivity).navigateTo(RegisterFragment(), showBottomNav = false)
        }

        return view
    }
}
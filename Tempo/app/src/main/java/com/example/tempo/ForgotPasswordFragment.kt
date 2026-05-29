package com.example.tempo

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ForgotPasswordFragment : Fragment() {

    private lateinit var btnForgotPassword: Button
    private lateinit var tvSignupLink: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)

        btnForgotPassword = view.findViewById(R.id.btnForgotPassword)
        tvSignupLink = view.findViewById(R.id.tvSignupLink)

        btnForgotPassword.setOnClickListener {
            Toast.makeText(requireContext(), "Password reset link would be sent!", Toast.LENGTH_SHORT).show()
            (activity as MainActivity).navigateTo(LoginFragment(), showBottomNav = false)
        }

        tvSignupLink.setOnClickListener {
            (activity as MainActivity).navigateTo(RegisterFragment(), showBottomNav = false)
        }

        return view
    }
}

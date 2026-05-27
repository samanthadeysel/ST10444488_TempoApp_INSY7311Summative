package com.example.tempo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast

class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity

        view.findViewById<Button>(R.id.btnResetPassword).setOnClickListener {
            Toast.makeText(requireContext(), "Reset link sent!", Toast.LENGTH_SHORT).show()
            mainActivity.navigateTo(LoginFragment())
        }
    }
}
package com.example.tempo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment() {

    private lateinit var btnSignup: Button
    private lateinit var tvLoginLink: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        btnSignup = view.findViewById(R.id.btnSignup)
        tvLoginLink = view.findViewById(R.id.tvLoginLink)

        btnSignup.setOnClickListener {
            (activity as MainActivity).navigateTo(HomeFragment(), showBottomNav = true)
        }

        tvLoginLink.setOnClickListener {
            (activity as MainActivity).navigateTo(LoginFragment(), showBottomNav = false)
        }

        return view
    }
}

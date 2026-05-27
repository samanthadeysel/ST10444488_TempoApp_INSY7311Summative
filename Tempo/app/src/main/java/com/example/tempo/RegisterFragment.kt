package com.example.tempo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button

class RegisterFragment : Fragment(R.layout.fragment_register) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity

        view.findViewById<Button>(R.id.btnSignup).setOnClickListener {
            mainActivity.navigateTo(HomeFragment())
        }
    }
}
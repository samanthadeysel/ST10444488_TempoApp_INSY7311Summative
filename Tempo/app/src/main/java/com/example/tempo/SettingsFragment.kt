package com.example.tempo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.Toast

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity

        view.findViewById<Button>(R.id.btnSaveSettings).setOnClickListener {
            Toast.makeText(requireContext(), "Settings saved!", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            mainActivity.navigateTo(LoginFragment())
        }
    }
}
package com.example.tempo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {
    private lateinit var btnEditUser: Button
    private lateinit var btnChangePassword: Button
    private lateinit var btnClearSchedule: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        //type casting
        btnEditUser = view.findViewById(R.id.btnEditUser)
        btnChangePassword = view.findViewById(R.id.btnChangePassword)
        btnClearSchedule = view.findViewById(R.id.btnClearSchedule)

        //on click listener
        btnEditUser.setOnClickListener {
            Toast.makeText(requireContext(), "Edit User Info clicked", Toast.LENGTH_SHORT).show()
        }

        btnChangePassword.setOnClickListener {
            Toast.makeText(requireContext(), "Change Password clicked", Toast.LENGTH_SHORT).show()
        }

        btnClearSchedule.setOnClickListener {
            Toast.makeText(requireContext(), "Schedule cleared", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
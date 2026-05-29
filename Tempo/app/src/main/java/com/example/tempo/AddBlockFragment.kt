package com.example.tempo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddBlockFragment : Fragment(R.layout.fragment_add_block) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnSave)?.setOnClickListener {
            Toast.makeText(requireContext(), "Block saved!", Toast.LENGTH_SHORT).show()
            (activity as MainActivity).navigateTo(ScheduleFragment(), showBottomNav = true)
        }
    }
}
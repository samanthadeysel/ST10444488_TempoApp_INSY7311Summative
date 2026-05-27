package com.example.tempo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddBlockFragment : Fragment(R.layout.fragment_add_block) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity

        view.findViewById<Button>(R.id.btn_save).setOnClickListener {
            Toast.makeText(requireContext(), "Block Saved!", Toast.LENGTH_SHORT).show()
            mainActivity.navigateTo(ScheduleFragment())
        }
    }
}
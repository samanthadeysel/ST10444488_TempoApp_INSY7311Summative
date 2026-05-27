package com.example.tempo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // You can hard-code or later fetch dynamic values
        view.findViewById<TextView>(R.id.tvGreeting).text = "Hello! Sammy"
        view.findViewById<TextView>(R.id.tvScheduleSummary).text = "Your Schedule for Today is\nFully Booked"
        view.findViewById<TextView>(R.id.tvDateTime).text = "Mon, 23 March\n09:25"
        view.findViewById<TextView>(R.id.tvCurrentBlock).text = "Currently:\nHon. Group Study"
    }
}

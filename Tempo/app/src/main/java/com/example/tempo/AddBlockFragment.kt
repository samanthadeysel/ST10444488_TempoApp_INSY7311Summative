package com.example.tempo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddBlockFragment : Fragment() {
    private lateinit var btnSave: Button

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_TIME_ALLOCATION = "arg_time_allocation"
        private const val ARG_DATE = "arg_date"
        private const val ARG_START_TIME = "arg_start_time"
        private const val ARG_CATEGORY = "arg_category"
        private const val ARG_DETAILS = "arg_details"

        fun newInstance(
            title: String = "",
            timeAllocation: String = "",
            date: String = "",
            startTime: String = "",
            category: String = "",
            details: String = ""
        ): AddBlockFragment {
            val b = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_TIME_ALLOCATION, timeAllocation)
                putString(ARG_DATE, date)
                putString(ARG_START_TIME, startTime)
                putString(ARG_CATEGORY, category)
                putString(ARG_DETAILS, details)
            }
            return AddBlockFragment().apply { arguments = b }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_block, container, false)

        //typecasting
        btnSave = view.findViewById(R.id.btnSave)

        //onclick listener
        btnSave.setOnClickListener {
            Toast.makeText(requireContext(), "Block saved!", Toast.LENGTH_SHORT).show()
            (activity as MainActivity).navigateTo(ScheduleFragment(), showBottomNav = true)
        }

        return view
    }
}
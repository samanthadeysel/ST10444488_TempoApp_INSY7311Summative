package com.example.tempo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class StudyBlockFragment : Fragment(R.layout.fragment_study_block) {
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

    private lateinit var etTitle: EditText
    private lateinit var etTimeAllocation: EditText
    private lateinit var etDate: EditText
    private lateinit var etStartTime: EditText
    private lateinit var etCategory: EditText
    private lateinit var etDetails: EditText
    private lateinit var btnSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_block, container, false)

        etTitle = view.findViewById(R.id.etTitle)
        etTimeAllocation = view.findViewById(R.id.etTimeAllocation)
        etDate = view.findViewById(R.id.etDate)
        etStartTime = view.findViewById(R.id.etStartTime)
        etCategory = view.findViewById(R.id.etCategory)
        etDetails = view.findViewById(R.id.etDetails)
        btnSave = view.findViewById(R.id.btnSave)

        // Prefill if arguments present
        arguments?.let { args ->
            etTitle.setText(args.getString(ARG_TITLE, ""))
            etTimeAllocation.setText(args.getString(ARG_TIME_ALLOCATION, ""))
            etDate.setText(args.getString(ARG_DATE, ""))
            etStartTime.setText(args.getString(ARG_START_TIME, ""))
            etCategory.setText(args.getString(ARG_CATEGORY, ""))
            etDetails.setText(args.getString(ARG_DETAILS, ""))
        }

        btnSave.setOnClickListener {
            // TODO: validate and save block (ViewModel / repository)
            // Example: viewModel.saveBlock(...)
            (activity as? MainActivity)?.navigateTo(ScheduleFragment(), showBottomNav = true)
        }

        return view
    }
}
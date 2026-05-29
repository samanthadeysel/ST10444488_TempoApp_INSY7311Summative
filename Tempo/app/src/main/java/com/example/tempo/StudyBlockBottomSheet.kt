package com.example.tempo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StudyBlockBottomSheet : BottomSheetDialogFragment() {

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_TYPE = "arg_type"
        private const val ARG_START = "arg_start"
        private const val ARG_FINISH = "arg_finish"
        private const val ARG_DETAILS = "arg_details"

        fun newInstance(
            title: String,
            type: String,
            start: String,
            finish: String,
            details: String
        ): StudyBlockBottomSheet {
            val frag = StudyBlockBottomSheet()
            frag.arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_TYPE, type)
                putString(ARG_START, start)
                putString(ARG_FINISH, finish)
                putString(ARG_DETAILS, details)
            }
            return frag
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the bottom-sheet layout (ensure this file exists and IDs match)
        return inflater.inflate(R.layout.fragment_study_block, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tvTitle = view.findViewById<TextView>(R.id.tvBlockTitle)
        val tvType = view.findViewById<TextView>(R.id.tvBlockType)
        val tvStart = view.findViewById<TextView>(R.id.tvStartTime)
        val tvFinish = view.findViewById<TextView>(R.id.tvFinishTime)
        val tvDetails = view.findViewById<TextView>(R.id.tvDetails)
        val btnStart = view.findViewById<Button>(R.id.btnStartTimer)
        val btnReschedule = view.findViewById<Button>(R.id.btnReschedule)

        if (tvTitle == null || btnStart == null || btnReschedule == null) {
            dismiss()
            return
        }

        val title = arguments?.getString(ARG_TITLE) ?: "Study Block"
        val type = arguments?.getString(ARG_TYPE) ?: "Study"
        val start = arguments?.getString(ARG_START) ?: ""
        val finish = arguments?.getString(ARG_FINISH) ?: ""
        val details = arguments?.getString(ARG_DETAILS) ?: ""

        tvTitle.text = title
        tvType.text = type
        tvStart.text = if (start.isNotBlank()) "Start: $start" else ""
        tvFinish.text = if (finish.isNotBlank()) "Finish: $finish" else ""
        tvDetails.text = details

        btnStart.setOnClickListener {
            val timerFrag = StudyTimerFragment.newInstance(
                title = title,
                startTime = start,
                finishTime = finish,
                details = details
            )
            (activity as? MainActivity)?.navigateTo(timerFrag, showBottomNav = false)
            dismiss()
        }

        btnReschedule.setOnClickListener {
            val addFrag = AddBlockFragment.newInstance(
                title = title,
                timeAllocation = finish,
                date = "",
                startTime = start,
                category = type,
                details = details
            )
            (activity as? MainActivity)?.navigateTo(addFrag, showBottomNav = false)
            dismiss()
        }
    }
}
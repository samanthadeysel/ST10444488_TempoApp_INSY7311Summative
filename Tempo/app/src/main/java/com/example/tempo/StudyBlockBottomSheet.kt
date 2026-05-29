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

    private lateinit var tvTitle: TextView
    private lateinit var tvType: TextView
    private lateinit var tvStart: TextView
    private lateinit var tvFinish: TextView
    private lateinit var tvDetails: TextView
    private lateinit var btnStart: Button
    private lateinit var btnReschedule: Button
    private lateinit var btnClose: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_study_block, container, false)

        tvTitle = view.findViewById(R.id.tvBlockTitle)
        tvType = view.findViewById(R.id.tvBlockType)
        tvStart = view.findViewById(R.id.tvStartTime)
        tvFinish = view.findViewById(R.id.tvFinishTime)
        tvDetails = view.findViewById(R.id.tvDetails)
        btnStart = view.findViewById(R.id.btnStartTimer)
        btnReschedule = view.findViewById(R.id.btnReschedule)

        val title = arguments?.getString(ARG_TITLE) ?: "Study Block"
        val type = arguments?.getString(ARG_TYPE) ?: "Study"
        val start = arguments?.getString(ARG_START) ?: ""
        val finish = arguments?.getString(ARG_FINISH) ?: ""
        val details = arguments?.getString(ARG_DETAILS) ?: ""

        tvTitle.text = title
        tvType.text = type
        tvStart.text = if (start.isNotEmpty()) "Start: $start" else ""
        tvFinish.text = if (finish.isNotEmpty()) "Finish: $finish" else ""
        tvDetails.text = details

        btnClose.setOnClickListener { dismiss() }

        //start
        btnStart.setOnClickListener {
            val timerFrag = StudyTimerFragment.newInstance(
                title = title,
                startTime = start,
                finishTime = finish,
                details = details
            )

            // Use activity navigation helper to replace fragment and hide bottom nav
            (activity as? MainActivity)?.navigateTo(timerFrag, showBottomNav = false)

            // dismiss bottom sheet
            dismiss()
        }

        //reschedule
        btnReschedule.setOnClickListener {
            val addFrag = AddBlockFragment.newInstance(
                title = title,
                timeAllocation = finish, // or pass finish separately if you have a finish field
                date = "",               // optional: pass date if available
                startTime = start,
                category = type,
                details = details
            )

            (activity as? MainActivity)?.navigateTo(addFrag, showBottomNav = false)
            dismiss()
        }

        return view
    }
}

package com.example.tempo

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class StudyBlockFragment : Fragment(R.layout.fragment_study_block) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity

        view.findViewById<Button>(R.id.btnBackToSchedule).setOnClickListener {
            mainActivity.navigateTo(ScheduleFragment())
        }
    }
}
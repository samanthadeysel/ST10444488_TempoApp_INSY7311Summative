package com.example.tempo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as MainActivity

        view.findViewById<TextView>(R.id.tvBlock1).setOnClickListener {
            mainActivity.navigateTo(StudyBlockFragment())
        }
        view.findViewById<TextView>(R.id.tvBlock2).setOnClickListener {
            mainActivity.navigateTo(StudyBlockFragment())
        }
        view.findViewById<TextView>(R.id.tvBlock3).setOnClickListener {
            mainActivity.navigateTo(StudyBlockFragment())
        }

        // Buttons still work
        view.findViewById<Button>(R.id.btnAddBlock).setOnClickListener {
            mainActivity.navigateTo(AddBlockFragment())
        }
    }
}

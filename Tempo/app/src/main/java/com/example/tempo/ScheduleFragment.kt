package com.example.tempo

import Data.ScheduleItem
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScheduleFragment : Fragment() {

    private lateinit var tvTitle: TextView
    private lateinit var tvDate: TextView
    private lateinit var rvSchedule: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        // Bind views
        tvTitle = view.findViewById(R.id.tvTitle)
        tvDate = view.findViewById(R.id.tvDate)
        rvSchedule = view.findViewById(R.id.rvSchedule)


        // Static schedule data
        val scheduleItems = listOf(
            ScheduleItem("06:00", "Morning routine", "Free"),
            ScheduleItem("08:00", "Grad. Speech prep", "Study"),
            ScheduleItem("09:00", "Hon. Group", "Study"),
            ScheduleItem("10:00", "English Lit", "Study"),
            ScheduleItem("10:30", "Statistics", "Study"),
            ScheduleItem("11:00", "Coffee w Friends", "Free"),
            ScheduleItem("11:30", "Economics", "Study"),
            ScheduleItem("13:30", "Lunch", "Free"),
            ScheduleItem("14:00", "Accounting 3C", "Study"),
            ScheduleItem("15:00", "Group Assign.", "Study"),
            ScheduleItem("16:30", "Bowling", "Free")
        )

        // RecyclerView setup
        rvSchedule.layoutManager = LinearLayoutManager(requireContext())
        rvSchedule.adapter = ScheduleAdapter(scheduleItems)

        return view
    }
}
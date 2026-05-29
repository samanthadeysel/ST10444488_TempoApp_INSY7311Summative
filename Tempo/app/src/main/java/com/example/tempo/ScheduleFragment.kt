package com.example.tempo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class ScheduleFragment : Fragment() {

    private lateinit var rvSchedule: RecyclerView
    private lateinit var adapter: ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_schedule, container, false)

        rvSchedule = view.findViewById(R.id.rvSchedule)
        rvSchedule.layoutManager = LinearLayoutManager(requireContext())

        // Build rows to match the attached design (Morning / Afternoon sections)
        val rows = mutableListOf<ScheduleRow>()

        rows.add(ScheduleRow.Section("Morning"))
        rows.add(ScheduleRow.Item("06:00", "Morning routine", status = "Free"))
        rows.add(ScheduleRow.Item("08:00", "Grad. Speech prep", status = "Study"))
        rows.add(ScheduleRow.Item("09:00", "Hon. Group", status = "Study"))
        rows.add(ScheduleRow.Item("10:00", "English Lit", status = "Study"))
        rows.add(ScheduleRow.Item("10:30", "Statistics", status = "Study"))
        rows.add(ScheduleRow.Item("11:00", "Coffee w Friends", status = "Free"))
        rows.add(ScheduleRow.Item("11:30", "Economics", status = "Study"))

        rows.add(ScheduleRow.Section("Afternoon"))
        rows.add(ScheduleRow.Item("13:30", "Lunch", status = "Free"))
        rows.add(ScheduleRow.Item("14:00", "Accounting 3C", status = "Study"))
        rows.add(ScheduleRow.Item("15:00", "Group Assign.", status = "Study"))
        rows.add(ScheduleRow.Item("16:30", "Bowling", status = "Free"))

        adapter = ScheduleAdapter(rows)
        rvSchedule.adapter = adapter

        // Set date text
        val tvDate = view.findViewById<android.widget.TextView>(R.id.tvScheduleDate)
        val sdf = SimpleDateFormat("EEE, d MMMM", Locale.getDefault())
        tvDate.text = sdf.format(Date())

        return view
    }
}

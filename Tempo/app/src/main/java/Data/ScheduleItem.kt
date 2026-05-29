package com.example.tempo

sealed class ScheduleRow {
    data class Section(val title: String) : ScheduleRow()
    data class Item(
        val time: String,
        val activity: String,
        val details: String = "",
        val status: String = "Free"
    ) : ScheduleRow()
}
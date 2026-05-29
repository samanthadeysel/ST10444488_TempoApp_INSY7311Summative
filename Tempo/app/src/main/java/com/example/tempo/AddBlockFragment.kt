package com.example.tempo

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class AddBlockFragment : Fragment() {
    private lateinit var btnSave: Button
    private var etDate: EditText? = null

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

    private val displayDateFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_block, container, false)

        btnSave = view.findViewById(R.id.btnSave)
        etDate = view.findViewById(R.id.etDate)

        arguments?.let { args ->
            val dateArg = args.getString(ARG_DATE, "")
            if (dateArg.isNotBlank()) {
                etDate?.setText(dateArg)
            }
        }

        etDate?.setOnClickListener {
            showDatePicker()
        }

        etDate?.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) showDatePicker()
        }

        btnSave.setOnClickListener {
            Toast.makeText(requireContext(), "Block saved!", Toast.LENGTH_SHORT).show()
            (activity as MainActivity).navigateTo(ScheduleFragment(), showBottomNav = true)
        }

        return view
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val currentText = etDate?.text?.toString()
        if (!currentText.isNullOrBlank()) {
            try {
                val parsed = displayDateFormat.parse(currentText)
                if (parsed != null) calendar.time = parsed
            } catch (_: Exception) { /* ignore and use today */ }
        }

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireContext(), { _, selYear, selMonth, selDay ->
            val selCal = Calendar.getInstance().apply {
                set(Calendar.YEAR, selYear)
                set(Calendar.MONTH, selMonth)
                set(Calendar.DAY_OF_MONTH, selDay)
            }
            etDate?.setText(displayDateFormat.format(selCal.time))
        }, year, month, day)
        dpd.show()
    }
}

package com.example.tempo

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.progressindicator.CircularProgressIndicator
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class StudyTimerFragment : Fragment() {

    companion object {
        private const val ARG_TITLE = "arg_title"
        private const val ARG_START = "arg_start"
        private const val ARG_FINISH = "arg_finish"
        private const val ARG_DETAILS = "arg_details"

        fun newInstance(title: String, startTime: String, finishTime: String, details: String): StudyTimerFragment {
            val f = StudyTimerFragment()
            f.arguments = Bundle().apply {
                putString(ARG_TITLE, title)
                putString(ARG_START, startTime)
                putString(ARG_FINISH, finishTime)
                putString(ARG_DETAILS, details)
            }
            return f
        }
    }

    private var countDownTimer: CountDownTimer? = null
    private var totalMillis: Long = 0L
    private var remainingMillis: Long = 0L

    private var tvTitle: TextView? = null
    private var tvRemaining: TextView? = null
    private var tvCurrentTimeTop: TextView? = null
    private var cpiProgress: CircularProgressIndicator? = null
    private var btnStop: Button? = null
    private var tvType: TextView? = null
    private var tvFinishLabel: TextView? = null

    private val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val clockHandler = Handler(Looper.getMainLooper())
    private val clockRunnable = object : Runnable {
        override fun run() {
            try {
                tvCurrentTimeTop?.text = timeFormat.format(Date())
            } catch (_: Exception) { /* ignore */ }
            val millis = System.currentTimeMillis()
            val delay = 60000 - (millis % 60000)
            clockHandler.postDelayed(this, delay)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_study_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // bind views safely
        tvTitle = view.findViewById(R.id.tvTimerTitle)
        tvType = view.findViewById(R.id.tvTimerType)
        tvFinishLabel = view.findViewById(R.id.tvFinishLabel)
        tvRemaining = view.findViewById(R.id.tvTimerRemaining)
        tvCurrentTimeTop = view.findViewById(R.id.tvCurrentTimeTop)
        cpiProgress = view.findViewById(R.id.cpiProgress)
        btnStop = view.findViewById(R.id.btnStopTimer)

        // Defensive: ensure progress indicator exists before using it
        cpiProgress?.let {
            it.isIndeterminate = false
            it.max = 100
            it.progress = 100
        }

        val title = arguments?.getString(ARG_TITLE) ?: "Study"
        val start = arguments?.getString(ARG_START) ?: ""
        val finish = arguments?.getString(ARG_FINISH) ?: ""
        val details = arguments?.getString(ARG_DETAILS) ?: ""

        tvTitle?.text = title
        tvType?.text = details.takeIf { it.isNotBlank() } ?: "Study Time"
        tvFinishLabel?.text = if (finish.isNotBlank()) "Finish Time: $finish" else ""

        // compute total and remaining millis
        totalMillis = computeMillisBetween(start, finish).coerceAtLeast(TimeUnit.MINUTES.toMillis(1))
        remainingMillis = totalMillis

        // startCountdown is started in onResume to respect lifecycle
        btnStop?.setOnClickListener {
            cancelTimers()
            (activity as? MainActivity)?.navigateTo(ScheduleFragment(), showBottomNav = true)
        }
    }

    override fun onResume() {
        super.onResume()
        // start clock and countdown when fragment is visible
        clockHandler.post(clockRunnable)
        startCountdown(remainingMillis)
    }

    override fun onPause() {
        super.onPause()
        // stop updates while not visible
        clockHandler.removeCallbacks(clockRunnable)
        countDownTimer?.cancel()
    }

    private fun startCountdown(durationMillis: Long) {
        countDownTimer?.cancel()

        // initialize UI
        tvRemaining?.text = formatMillis(durationMillis)
        // animate to full (100) if needed
        cpiProgress?.let { setProgressAnimated(it.progress, 100) }

        countDownTimer = object : CountDownTimer(durationMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                remainingMillis = millisUntilFinished
                tvRemaining?.text = formatMillis(millisUntilFinished)

                // compute remaining percent (0..100)
                val percent = ((millisUntilFinished.toDouble() / totalMillis.toDouble()) * 100.0).toInt()
                cpiProgress?.progress = percent.coerceIn(0, 100)
            }

            override fun onFinish() {
                remainingMillis = 0L
                tvRemaining?.text = "00:00"
                cpiProgress?.progress = 0
            }
        }.start()
    }

    private fun setProgressAnimated(from: Int, to: Int) {
        val animator = ValueAnimator.ofInt(from, to)
        animator.duration = 250
        animator.addUpdateListener { animation ->
            cpiProgress?.progress = animation.animatedValue as Int
        }
        animator.start()
    }

    private fun computeMillisBetween(start: String, finish: String): Long {
        return try {
            val fmt = SimpleDateFormat("HH:mm", Locale.getDefault())
            val s = fmt.parse(start)
            val f = fmt.parse(finish)
            if (s != null && f != null) {
                var diff = f.time - s.time
                if (diff <= 0) diff += TimeUnit.DAYS.toMillis(1) // handle overnight
                diff
            } else {
                TimeUnit.MINUTES.toMillis(25)
            }
        } catch (e: Exception) {
            TimeUnit.MINUTES.toMillis(25)
        }
    }

    private fun formatMillis(ms: Long): String {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(ms)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(minutes)
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
    }

    private fun cancelTimers() {
        countDownTimer?.cancel()
        countDownTimer = null
        clockHandler.removeCallbacks(clockRunnable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cancelTimers()
        // clear view refs
        tvTitle = null
        tvType = null
        tvFinishLabel = null
        tvRemaining = null
        tvCurrentTimeTop = null
        cpiProgress = null
        btnStop = null
    }
}

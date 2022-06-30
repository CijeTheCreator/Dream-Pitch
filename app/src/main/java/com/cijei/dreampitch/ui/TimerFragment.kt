package com.cijei.dreampitch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cijei.dreampitch.R
import com.cijei.dreampitch.hood.MinuteCountDown
import com.cijei.dreampitch.hood.SecondCountDown

class TimerFragment: Fragment() {

    private var tempPauseMinuteData: Int = 0
    private var tempPauseSecondData: Int = 0
    private var isPaused = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_timer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startCountDown(view, 2)
    }

    private fun startCountDown(view: View, minutes: Long, secondsCount: Int = 0, minutesCount: Int = 0) {
        val minutesToMilliSeconds = minutes * 60 * 1000
        val minuteTextView = view.findViewById<TextView>(R.id.minuteTextView)
        val secondTextView = view.findViewById<TextView>(R.id.secondTextView)
        val secondCountDown = SecondCountDown(60000, 1000, secondTextView)
        val minuteCountDown = MinuteCountDown(minutesToMilliSeconds, 60000, minuteTextView, 0, this.context, secondCountDown)
        minuteCountDown.count = minutesCount
        minuteCountDown.secondCountDown.count = secondsCount

        minuteCountDown.start()
        stopCountDown(view, minuteCountDown)
        pauseCountDown(view, minuteCountDown)
    }

    private fun stopCountDown(view: View, countdown: MinuteCountDown, currentMinutes: String = "00", currentSeconds: String = "00") {
        val stopButton = view.findViewById<Button>(R.id.stop_button)
        val minuteTextView = view.findViewById<TextView>(R.id.minuteTextView)
        val secondTextView = view.findViewById<TextView>(R.id.secondTextView)
        stopButton.setOnClickListener {
            countdown.cancel()
            countdown.secondCountDown.cancel()
            countdown.count = 0
            countdown.secondCountDown.count = 0
            minuteTextView.text = currentMinutes
            secondTextView.text = currentSeconds
        }
    }

    private fun pauseCountDown(view: View, countdown: MinuteCountDown) {
        val pauseButton = view.findViewById<Button>(R.id.pause_button)
        val minuteTextView = view.findViewById<TextView>(R.id.minuteTextView)
        val secondTextView = view.findViewById<TextView>(R.id.secondTextView)

        pauseButton.setOnClickListener {
            if (!isPaused) {
                stopCountDown(view, countdown, tempPauseMinuteData.toString(), tempPauseSecondData.toString())
                tempPauseSecondData = countdown.secondCountDown.count
                tempPauseMinuteData = countdown.count
                isPaused = true
            } else {
                startCountDown(view, tempPauseMinuteData.toLong(), tempPauseMinuteData, tempPauseSecondData)
            }
        }

    }
}
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

    private var tempPauseMinuteData = null
    private var tempPauseSecondData = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_timer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val countdown = startCountDown(view, 2)
        stopCountDown(view, countdown)
    }

    private fun startCountDown(view: View, minutes: Long): MinuteCountDown {
        val minutesToMilliSeconds = minutes * 60 * 1000
        val minuteTextView = view.findViewById<TextView>(R.id.minuteTextView)
        val secondTextView = view.findViewById<TextView>(R.id.secondTextView)
        val secondCountDown = SecondCountDown(60000, 1000, secondTextView)
        val minuteCountDown = MinuteCountDown(minutesToMilliSeconds, 60000, minuteTextView, 0, this.context, secondCountDown)

        minuteCountDown.start()
        return minuteCountDown
    }

    private fun stopCountDown(view: View, countdown: MinuteCountDown) {
        val stopButton = view.findViewById<Button>(R.id.stop_button)
        val minuteTextView = view.findViewById<TextView>(R.id.minuteTextView)
        val secondTextView = view.findViewById<TextView>(R.id.secondTextView)
        stopButton.setOnClickListener {
            countdown.cancel()
            countdown.secondCountDown.cancel()
            minuteTextView.text = "00"
            secondTextView.text = "00"
        }
    }
}
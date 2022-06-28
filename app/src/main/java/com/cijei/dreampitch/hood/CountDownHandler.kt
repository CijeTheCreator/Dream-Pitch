package com.cijei.dreampitch.hood

import android.content.Context
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast

class MinuteCountDown(
    length: Long,
    interval: Long,
    private val minuteTextView: TextView,
    private var count: Int,
    private val context: Context?,
    private val secondCountDown: SecondCountDown
): CountDownTimer(length, interval) {

    override fun onTick(p0: Long) {
        count++
        minuteTextView.text = count.toString()
        secondCountDown.cancel()
        secondCountDown.start()
    }

    override fun onFinish() {
        Toast.makeText(context, "Finished", Toast.LENGTH_LONG).show()
    }


}

class SecondCountDown(
    length: Long,
    interval: Long,
    private val secondTextView: TextView,
    private var count: Int
): CountDownTimer(length, interval) {

    override fun onTick(p0: Long) {
        secondTextView.text = count.toString()
        count++

    }

    override fun onFinish() {
        secondTextView.text = "00"
        count = 0
    }

}

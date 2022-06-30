package com.cijei.dreampitch.hood

import android.content.Context
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast

class MinuteCountDown(
    length: Long,
    interval: Long,
    private val minuteTextView: TextView,
    var count: Int,
    private val context: Context?,
    val secondCountDown: SecondCountDown
): CountDownTimer(length, interval) {

    override fun onTick(p0: Long) {
        if (count >= 10) {
            minuteTextView.text = count.toString()
        } else {
            minuteTextView.text = "0${count.toString()}"
        }
        count++
        secondCountDown.count = 0
        secondCountDown.cancel()
        secondCountDown.start()
    }

    override fun onFinish() {
        Toast.makeText(context, "Finished", Toast.LENGTH_LONG).show()
        minuteTextView.text = "0${count++.toString()}"
    }


}

class SecondCountDown(
    length: Long,
    interval: Long,
    private val secondTextView: TextView,
): CountDownTimer(length, interval) {

    var count: Int = 0
    override fun onTick(p0: Long) {
        if (count >= 10) {
            secondTextView.text = count.toString()
        } else {
            secondTextView.text = "0${count.toString()}"
        }
        count++

    }

    override fun onFinish() {
        secondTextView.text = "00"
        count = 0
    }

}

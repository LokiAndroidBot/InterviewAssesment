package com.indalph.interviewassessment

import android.util.Log

fun println(anything: Any?) {
    Log.e("To Print", anything?.toString() ?: "")
}

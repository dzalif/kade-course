package com.kucingselfie.kotlindicodingsubmission2.util

import android.view.View
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun String.toGMT7(): String? {
    if (this.isNotEmpty()) {
        val dateFormat = SimpleDateFormat("HH:mm:ssZ", Locale.US)

        try {
            val date = dateFormat.parse(this)
            return DateFormat.getTimeInstance(DateFormat.SHORT, Locale.getDefault()).format(date!!)

        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
    return null
}
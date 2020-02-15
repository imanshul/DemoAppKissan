package com.example.demoappkissan.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/*
*Created By Anshul Thakur on 2/15/2020.
*/
@SuppressLint("SimpleDateFormat")
object DateTimeUtils{

    const val DEFAULT_FORMAT = "dd MMM yy, hh:mm a"

    fun getDateAndTimeFromMillies(toLong: Long): String {
        return SimpleDateFormat(DEFAULT_FORMAT).format(toLong)
    }
}

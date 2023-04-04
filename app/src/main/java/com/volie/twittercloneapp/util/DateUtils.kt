package com.volie.twittercloneapp.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val dateFormat = SimpleDateFormat("MM/yyyy", Locale.getDefault())

    fun getFormatDate(timeInMillis: Long): String {
        return dateFormat.format(Date(timeInMillis))
    }
}
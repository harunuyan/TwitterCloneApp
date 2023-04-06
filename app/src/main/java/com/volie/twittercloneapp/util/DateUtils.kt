package com.volie.twittercloneapp.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val joinedDateFormat = SimpleDateFormat("MM/yyyy", Locale.getDefault())

    fun getJoinedDate(timeInMillis: Long): String {
        return joinedDateFormat.format(Date(timeInMillis))
    }
}
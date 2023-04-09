package com.volie.twittercloneapp.view.fragment.viewmodel

import androidx.lifecycle.ViewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PostDetailsViewModel
@Inject constructor() : ViewModel() {

    fun formatMillis(millis: Long): String {
        val dateFormat: DateFormat = SimpleDateFormat("hh:mm a dd MMM yy", Locale.getDefault())
        return dateFormat.format(Date(millis))
    }

    private fun getPostComments() {

    }
}
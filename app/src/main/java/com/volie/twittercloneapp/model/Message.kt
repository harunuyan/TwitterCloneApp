package com.volie.twittercloneapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(
    val profilePhoto: String,
    val senderNickname: String,
    val senderName: String,
    val message: String,
    val time: String,
) : Parcelable
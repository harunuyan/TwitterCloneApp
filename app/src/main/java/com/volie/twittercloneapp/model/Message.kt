package com.volie.twittercloneapp.model

data class Message(
    val profilePhoto: String,
    val senderNickname: String,
    val senderName: String,
    val message: String,
    val time: String,
)
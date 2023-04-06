package com.volie.twittercloneapp.model

data class Trend(
    val subject: String,
    val tag: String,
    val count: String,
    val video: TrendVideo
)

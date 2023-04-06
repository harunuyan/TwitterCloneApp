package com.volie.twittercloneapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trend(
    val subject: String,
    val tag: String,
    val count: String,
    val video: TrendVideo
) : Parcelable

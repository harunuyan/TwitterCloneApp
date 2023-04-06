package com.volie.twittercloneapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TrendVideo(
    val video: String
) : Parcelable
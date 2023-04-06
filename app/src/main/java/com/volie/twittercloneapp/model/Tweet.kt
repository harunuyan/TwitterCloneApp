package com.volie.twittercloneapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tweet(
    var id: String? = "",
    val text: String? = null,
    val createdAt: String? = null,
    val trend: Trend? = null,
    val viewCount: Int? = 0,
    val retweetCount: Int? = 0,
    val favoriteCount: Int? = 0,
    val retweeted: Boolean = false,
    val favorited: Boolean = false,
    val replyTo: Tweet? = null
) : Parcelable

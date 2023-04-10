package com.volie.twittercloneapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tweet(
    var id: String? = "",
    val text: String? = "",
    var postImage: String? = null,
    val createdAt: String? = null,
    val trend: Trend? = null,
    val comment: Comment? = null,
    val viewCount: Int? = 0,
    val retweetCount: Int? = 0,
    val quoteCount: Int? = 0,
    val favoriteCount: Int? = 0,
    val retweeted: Boolean = false,
    var favorited: Boolean = false,
    val replyTo: Tweet? = null
) : Parcelable

package com.volie.twittercloneapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Home(
    val id: Int,
    val nickname: String,
    val username: String?,
    val profileImage: String,
    val postText: String,
    val postImage: String,
    val retweet: String,
    val like: Int,
    var isLiked: Boolean,
    val comment: String,
    val quote: String,
    val time: String,
    val view: String
) : Parcelable

package com.volie.twittercloneapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment(
    val commentId: String? = null,
    val userId: String? = null,
    val tweet: Tweet? = null,
    val postImage: String? = null,
    val timestamp: Long? = null
) : Parcelable

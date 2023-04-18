package com.volie.twittercloneapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment(
    var commentId: String? = null,
    val userId: String? = null,
    val userProfilePhoto: String? = null,
    val fullName: String? = null,
    val username: String? = null,
    val tweet: Tweet? = null
) : Parcelable

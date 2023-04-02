package com.volie.twittercloneapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String? = null,
    val profileHeaderUrl: String? = "picsum.photos/200/300",
    val profileImageUrl: String? = "picsum.photos/200/300",
    val username: String,
    val nickname: String,
    val email: String,
    val bio: String? = "",
    val location: String? = "",
    val joinedDate: String? = null,
    val following: Int? = 0,
    val followers: Int? = 0,
) : Parcelable

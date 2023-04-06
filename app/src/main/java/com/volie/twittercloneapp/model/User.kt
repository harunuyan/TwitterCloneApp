package com.volie.twittercloneapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: String? = "",
    val name: String = "",
    val nickname: String = "",
    val tweet: Tweet? = null,
    val message: Message? = null,
    val profileImageUrl: String = "",
    val bio: String? = null,
    val location: String? = null,
    val followersCount: Int = 0,
    val followingCount: Int = 0
) : Parcelable

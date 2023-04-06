package com.volie.twittercloneapp.view.fragment.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.volie.twittercloneapp.model.User

class AddPostViewModel : ViewModel() {

    fun addPostToFirebase(user: User) {
        val tweetRef = FirebaseDatabase.getInstance().getReference("Tweet")
        val tweetId = tweetRef.push().key
        user.tweet?.id = tweetId!!
        tweetRef.child(tweetId).setValue(user.tweet)

        val postRef = FirebaseDatabase.getInstance().getReference("User")
        val postId = postRef.push().key
        user.id = postId!!
        postRef.child(postId).setValue(user)
    }
}
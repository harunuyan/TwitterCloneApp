package com.volie.twittercloneapp.view.fragment.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.volie.twittercloneapp.model.Tweet

class AddPostViewModel : ViewModel() {

    fun addPostToFirebase(tweet: Tweet) {
        val postRef = FirebaseDatabase.getInstance().getReference("Tweet")
        val postId = postRef.push().key
        postRef.child(postId!!).setValue(tweet)
    }
}
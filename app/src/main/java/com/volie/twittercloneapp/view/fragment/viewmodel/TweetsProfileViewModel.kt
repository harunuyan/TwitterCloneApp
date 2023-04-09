package com.volie.twittercloneapp.view.fragment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.volie.twittercloneapp.model.User

class TweetsProfileViewModel : ViewModel() {

    private val _tweets = MutableLiveData<List<User>>()
    val tweets: LiveData<List<User>> = _tweets

    private val database = FirebaseDatabase.getInstance()

    fun getTweets() {
        val tweetRef = database.getReference("User")
        val tweetListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tweetList = snapshot.children.mapNotNull {
                    it.getValue(User::class.java)
                }
                _tweets.postValue(tweetList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TweetsProfileViewModel", "onCancelled: ${error.message}")
            }
        }
        tweetRef.addValueEventListener(tweetListener)
    }
}
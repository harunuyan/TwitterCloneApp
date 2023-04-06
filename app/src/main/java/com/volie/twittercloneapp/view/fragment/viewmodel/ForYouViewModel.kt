package com.volie.twittercloneapp.view.fragment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.volie.twittercloneapp.model.Tweet
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel
@Inject constructor() : ViewModel() {

    private val _tweets = MutableLiveData<List<Tweet>>()
    val tweets: LiveData<List<Tweet>> = _tweets

    private val database = FirebaseDatabase.getInstance()

    fun getTweets() {
        val tweetRef = database.getReference("Tweet")
        val tweetListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tweetList = snapshot.children.mapNotNull {
                    it.getValue(Tweet::class.java)
                }
                _tweets.postValue(tweetList)
            }

            override fun onCancelled(e: DatabaseError) {
                Log.e("ForYouViewModel", "onCancelled: ${e.message}")
            }
        }
        tweetRef.addValueEventListener(tweetListener)
    }
}
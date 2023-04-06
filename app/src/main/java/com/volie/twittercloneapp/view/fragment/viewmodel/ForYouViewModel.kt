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
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel
@Inject constructor() : ViewModel() {

    private val _tweets = MutableLiveData<List<User>>()
    val tweets: LiveData<List<User>> = _tweets

    private val database = FirebaseDatabase.getInstance()

    fun getTweets() {
        val tweetRef = database.getReference("User")
        val tweetListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tweetList = snapshot.children.mapNotNull {
                    val uid = it.key
                    val data = it.getValue(User::class.java)
                    data?.id = uid
                    data
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
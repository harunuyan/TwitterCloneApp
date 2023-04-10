package com.volie.twittercloneapp.view.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.volie.twittercloneapp.model.User

class CommentViewModel : ViewModel() {

    private val user = FirebaseAuth.getInstance().currentUser
    private val database = FirebaseDatabase.getInstance()

    private val _tweets = MutableLiveData<List<User>>()
    val tweets: LiveData<List<User>> = _tweets

}
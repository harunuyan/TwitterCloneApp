package com.volie.twittercloneapp.view.fragment

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.volie.twittercloneapp.model.Home
import com.volie.twittercloneapp.model.HomeWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel
@Inject constructor() : ViewModel() {

    val listPost = MutableLiveData<List<Home>>()
    val isLiked = MutableLiveData<Boolean>()

    fun getPost(context: Context) {
        val json: String?

        val inputStream = context.assets.open("postResponse.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.use { it.read(buffer) }
        json = String(buffer)

        Log.i("TAG", "getPost: $json")
        val response = Gson().fromJson(json, HomeWrapper::class.java)
        listPost.postValue(response)
    }
}
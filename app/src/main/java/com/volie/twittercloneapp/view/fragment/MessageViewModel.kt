package com.volie.twittercloneapp.view.fragment

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.volie.twittercloneapp.model.Message
import com.volie.twittercloneapp.model.MessageWrapper

class MessageViewModel : ViewModel() {

    val listMessage = MutableLiveData<List<Message>>()

    fun getMessage(context: Context) {
        val json: String?

        val inputStream = context.assets.open("messageResponse.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.use { it.read(buffer) }
        json = String(buffer)

        Log.i("TAG", "getPost: $json")
        val response = Gson().fromJson(json, MessageWrapper::class.java)
        listMessage.postValue(response)
    }
}
package com.volie.twittercloneapp.view.fragment

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.volie.twittercloneapp.model.Trend
import com.volie.twittercloneapp.model.TrendVideo
import com.volie.twittercloneapp.model.TrendVideoWrapper
import com.volie.twittercloneapp.model.TrendWrapper

class TrendsViewModel : ViewModel() {

    val listVideo = MutableLiveData<List<TrendVideo>>()
    val listTrend = MutableLiveData<List<Trend>>()

    fun getVideo(context: Context) {
        val json: String?

        val inputStream = context.assets.open("trendVideoResponse.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.use { it.read(buffer) }
        json = String(buffer)

        Log.i("TAG", "getPost: $json")
        val response = Gson().fromJson(json, TrendVideoWrapper::class.java)
        listVideo.postValue(response)
    }

    fun getTrend(context: Context) {
        val json: String?

        val inputStream = context.assets.open("trendResponse.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.use { it.read(buffer) }
        json = String(buffer)

        Log.i("TAG", "getPost: $json")
        val response = Gson().fromJson(json, TrendWrapper::class.java)
        listTrend.postValue(response)
    }
}
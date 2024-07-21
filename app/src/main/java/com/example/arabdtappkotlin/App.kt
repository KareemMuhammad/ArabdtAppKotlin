package com.example.arabdtappkotlin

import android.app.Application
import com.example.arabdtappkotlin.data.source.remote.RetrofitClient

class App : Application() {

    companion object {
        lateinit var retrofit: RetrofitClient
    }
    override fun onCreate() {
        super.onCreate()
        retrofit = RetrofitClient
    }
}
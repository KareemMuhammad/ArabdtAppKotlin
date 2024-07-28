package com.example.arabdtappkotlin

import android.app.Application
import androidx.room.Room
import com.example.arabdtappkotlin.data.source.local.AppRoomDatabase
import com.example.arabdtappkotlin.data.source.remote.RetrofitClient

class App : Application() {

    companion object {
        val retrofitClient: RetrofitClient by lazy { RetrofitClient }
        lateinit var appDatabase: AppRoomDatabase
    }
    override fun onCreate() {
        super.onCreate()
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppRoomDatabase::class.java, "arabDT_database"
        ).build()
    }
}
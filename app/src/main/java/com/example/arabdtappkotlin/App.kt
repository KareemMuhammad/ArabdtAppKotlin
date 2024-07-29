package com.example.arabdtappkotlin

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.arabdtappkotlin.data.source.local.AppRoomDatabase
import com.example.arabdtappkotlin.data.source.remote.RetrofitClient

class App : Application() {

    companion object {
        val retrofitClient: RetrofitClient by lazy { RetrofitClient }
        lateinit var appDatabase: AppRoomDatabase
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        initNotification()
        appDatabase = Room.databaseBuilder(
            applicationContext,
            AppRoomDatabase::class.java, "arabDT_database"
        ).build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initNotification() {
        val notificationChannel = NotificationChannel(
            "arabdt",
            "ArabDT",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
    }
}
package com.example.arabdtappkotlin.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.arabdtappkotlin.data.models.UserSavedData

@Database(entities = [(UserSavedData::class)], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
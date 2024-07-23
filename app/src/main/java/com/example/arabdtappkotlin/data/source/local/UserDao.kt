package com.example.arabdtappkotlin.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.arabdtappkotlin.data.models.UserSavedData

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserSavedData)

    @Delete
    fun clear(vararg user: UserSavedData)

    @Query("SELECT * FROM user_table LIMIT 1")
    fun get(): UserSavedData
}
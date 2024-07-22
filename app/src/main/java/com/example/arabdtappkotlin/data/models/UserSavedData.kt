package com.example.arabdtappkotlin.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.arabdtappkotlin.data.models.LoginDataModel.UserRole
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_table")
data class UserSavedData(
    @PrimaryKey val token: String = "",
    @ColumnInfo("email") val email: String = "",
    @ColumnInfo("full_name") val fullName: String = "",
    @ColumnInfo("employee") val employee: String = "",
    @ColumnInfo("role") val role: UserRole = UserRole.EMPLOYEE
)

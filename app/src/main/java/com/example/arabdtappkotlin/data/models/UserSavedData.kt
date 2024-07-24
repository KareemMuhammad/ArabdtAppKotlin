package com.example.arabdtappkotlin.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.arabdtappkotlin.data.models.LoginDataModel.UserRole
import com.example.arabdtappkotlin.data.source.remote.ApiConstants

@Entity(tableName = "user_table")
data class UserSavedData(
    @PrimaryKey val token: String = "",
    @ColumnInfo("email") val email: String = "",
    @ColumnInfo("full_name") val fullName: String = "",
    @ColumnInfo("employee") val employee: String = "",
    @ColumnInfo("role") val role: UserRole = UserRole.EMPLOYEE,
    @ColumnInfo("image") val image: String = "",
)

fun UserSavedData.fromLoginModel(model: LoginDataModel): UserSavedData {
    return UserSavedData(
        model.token,
        model.email,
        model.fullName,
        model.employee,
        model.role,
        ApiConstants.IMAGE_BASE_URL + (model.userData?.profileImage ?: "")
    )
}

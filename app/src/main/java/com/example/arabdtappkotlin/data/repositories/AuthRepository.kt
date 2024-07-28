package com.example.arabdtappkotlin.data.repositories

import com.example.arabdtappkotlin.data.models.BaseResponse
import com.example.arabdtappkotlin.data.models.LoginDataModel
import com.example.arabdtappkotlin.data.models.UserSavedData
import retrofit2.Response

interface AuthRepository {
    suspend fun login(email: String, password: String): Response<BaseResponse<LoginDataModel>>?
    suspend fun getSavedUser(): UserSavedData
    suspend fun saveUser(user: UserSavedData)
}
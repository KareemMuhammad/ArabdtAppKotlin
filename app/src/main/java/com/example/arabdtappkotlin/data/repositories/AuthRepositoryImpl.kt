package com.example.arabdtappkotlin.data.repositories

import androidx.lifecycle.LiveData
import com.example.arabdtappkotlin.data.models.LoginResponse
import com.example.arabdtappkotlin.data.models.UserSavedData
import com.example.arabdtappkotlin.data.models.requests.LoginRequest
import com.example.arabdtappkotlin.data.source.local.AppRoomDatabase
import com.example.arabdtappkotlin.data.source.remote.AuthApiService
import retrofit2.Response

class AuthRepositoryImpl(val apiService: AuthApiService, val appRoomDatabase: AppRoomDatabase) :
    AuthRepository {
    override suspend fun login(email: String, password: String): Response<LoginResponse> {
        val request = LoginRequest(email, password)
        val response = apiService.login(request)
        return response
    }

    override suspend fun getSavedUser(): LiveData<UserSavedData> {
        return appRoomDatabase.userDao().get()
    }
}
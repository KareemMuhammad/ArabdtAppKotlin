package com.example.arabdtappkotlin.data.repositories

import com.example.arabdtappkotlin.data.models.BaseResponse
import com.example.arabdtappkotlin.data.models.LoginDataModel
import com.example.arabdtappkotlin.data.models.UserSavedData
import com.example.arabdtappkotlin.data.models.requests.LoginRequest
import com.example.arabdtappkotlin.data.source.local.AppRoomDatabase
import com.example.arabdtappkotlin.data.source.remote.AuthApiService
import retrofit2.Response

class AuthRepositoryImpl(
    private val apiService: AuthApiService,
    private val appRoomDatabase: AppRoomDatabase
) :
    AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Response<BaseResponse<LoginDataModel>> {
        val request = LoginRequest(email, password)
        val response = apiService.login(request)
        return response
    }

    override suspend fun getSavedUser(): UserSavedData {
        return appRoomDatabase.userDao().get()
    }

    override suspend fun saveUser(user: UserSavedData) {
        appRoomDatabase.userDao().insert(user = user)
    }
}
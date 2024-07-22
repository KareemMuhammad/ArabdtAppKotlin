package com.example.arabdtappkotlin.data.repositories

import androidx.lifecycle.LiveData
import com.example.arabdtappkotlin.data.models.LoginResponse
import com.example.arabdtappkotlin.data.models.UserSavedData
import retrofit2.Response

interface AuthRepository {
    suspend fun login(email: String, password: String): Response<LoginResponse>?
    suspend fun getSavedUser(): LiveData<UserSavedData>
}
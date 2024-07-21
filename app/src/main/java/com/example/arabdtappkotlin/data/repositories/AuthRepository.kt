package com.example.arabdtappkotlin.data.repositories

import com.example.arabdtappkotlin.data.models.LoginResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun login(email: String, password: String): Response<LoginResponse>?
}
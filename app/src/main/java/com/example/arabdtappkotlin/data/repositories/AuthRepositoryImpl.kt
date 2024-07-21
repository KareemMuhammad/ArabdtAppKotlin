package com.example.arabdtappkotlin.data.repositories

import com.example.arabdtappkotlin.data.models.LoginResponse
import com.example.arabdtappkotlin.data.models.requests.LoginRequest
import com.example.arabdtappkotlin.data.source.remote.AuthApiService
import retrofit2.Response

class AuthRepositoryImpl(val apiService: AuthApiService) : AuthRepository {
    override suspend fun login(email: String, password: String): Response<LoginResponse> {
            val request = LoginRequest(email,password)
            val response = apiService.login(request)
            return response
    }
}
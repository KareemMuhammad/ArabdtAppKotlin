package com.example.arabdtappkotlin.data.source.remote

import com.example.arabdtappkotlin.data.models.LoginDataModel
import com.example.arabdtappkotlin.data.models.LoginResponse
import com.example.arabdtappkotlin.data.models.requests.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {
    @GET("profile/profile")
    suspend fun getUserProfile(): LoginDataModel

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}

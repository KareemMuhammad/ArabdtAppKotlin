package com.example.arabdtappkotlin.networks

import com.example.arabdtappkotlin.model.LoginDataModel
import com.example.arabdtappkotlin.model.LoginResponse
import com.example.arabdtappkotlin.model.requests.LoginRequest
import com.google.gson.JsonObject
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

package com.example.arabdtappkotlin.networks

import com.example.arabdtappkotlin.models.LoginDataModel
import com.example.arabdtappkotlin.models.requests.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("profile/profile")
    suspend fun getUserProfile(): LoginDataModel

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginDataModel>
}

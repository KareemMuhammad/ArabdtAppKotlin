package com.example.arabdtappkotlin.data.source.remote

import com.example.arabdtappkotlin.data.models.BaseResponse
import com.example.arabdtappkotlin.data.models.LoginDataModel
import com.example.arabdtappkotlin.data.models.requests.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {
    @GET("profile/profile")
    suspend fun getUserProfile(): Response<BaseResponse<LoginDataModel>>

    @POST(ApiConstants.LOGIN_PATH)
    suspend fun login(@Body loginRequest: LoginRequest): Response<BaseResponse<LoginDataModel>>
}

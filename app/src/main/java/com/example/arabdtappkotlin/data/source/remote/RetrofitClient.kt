package com.example.arabdtappkotlin.data.source.remote

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit: Retrofit
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Log request and response bodies
    }

    init {
        println("::::Retrofit Initializing::::")
        retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(OkHTTPAppClient.okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authApiService: AuthApiService = retrofit.create(AuthApiService::class.java)
    val attendanceApiService: AttendanceApiService by lazy { retrofit.create(AttendanceApiService::class.java) }
}
package com.example.arabdtappkotlin.data.models

class BaseResponse<T>(
    val statusCode: Int,
    val message: String,
    val body: T?
)

package com.example.arabdtappkotlin.utils

sealed class AppState<out T>(
    val data: T? = null,
    val message: String? = null
) {
    class Init<T> : AppState<T>()

    class Success<T>(data: T?) : AppState<T>(data = data)

    class Error(message: String?) : AppState<Nothing>(data = null, message = message)

    class Loading<T>(data: T? = null) : AppState<T>(data = data)

}
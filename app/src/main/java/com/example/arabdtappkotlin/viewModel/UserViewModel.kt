package com.example.arabdtappkotlin.viewModel

import androidx.lifecycle.ViewModel
import com.example.arabdtappkotlin.model.LoginDataModel
import com.example.arabdtappkotlin.model.requests.LoginRequest
import com.example.arabdtappkotlin.networks.AuthApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class UiState {
    data object Init : UiState()
    data object Loading : UiState()
    data class Success(val data: LoginDataModel?) : UiState()
    data class Error(val ex: Exception) : UiState()
}

class UserViewModel (private val authApiService: AuthApiService) : ViewModel() {


    private val _state = MutableStateFlow<UiState>(UiState.Init)
    val state: StateFlow<UiState> get() = _state

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    suspend fun login(email: String, password: String) : LoginDataModel? {
        val request = LoginRequest(email,password)
            _state.value = UiState.Loading
           _isLoading.value = true
            try {
                val response = authApiService.login(request)
                if(response.isSuccessful) {
                  val userData = response.body()
                    _state.value = UiState.Success(userData?.body)
                    println("userData::: $userData")
                    _isLoading.value = false
                    return userData?.body
                }
            } catch (e: Exception) {
                _isLoading.value = false
                _state.value = UiState.Error(e)
            }
        return null
    }
}
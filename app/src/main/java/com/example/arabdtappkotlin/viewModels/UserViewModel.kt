package com.example.arabdtappkotlin.viewModels

import androidx.lifecycle.ViewModel
import com.example.arabdtappkotlin.models.LoginDataModel
import com.example.arabdtappkotlin.models.requests.LoginRequest
import com.example.arabdtappkotlin.networks.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class UiState {
    data object Init : UiState()
    data object Loading : UiState()
    data class Success(val data: LoginDataModel?) : UiState()
    data class Error(val ex: Exception) : UiState()
}

class UserViewModel (private val apiService: ApiService) : ViewModel() {


    private val _state = MutableStateFlow<UiState>(UiState.Init)
    val state: StateFlow<UiState> get() = _state

    suspend fun login(email: String, password: String) : LoginDataModel? {
        val request = LoginRequest(email,password)
            _state.value = UiState.Loading
            try {
                val response = apiService.login(request)
                _state.value = UiState.Success(response.body())
                return response.body()
            } catch (e: Exception) {
                _state.value = UiState.Error(e)
            }
        return null
    }
}
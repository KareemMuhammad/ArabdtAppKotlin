package com.example.arabdtappkotlin.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arabdtappkotlin.model.LoginDataModel
import com.example.arabdtappkotlin.model.requests.LoginRequest
import com.example.arabdtappkotlin.networks.AuthApiService
import com.example.arabdtappkotlin.utils.PreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState {
    data object Init : UiState()
    data object Loading : UiState()
    data class Success(val data: LoginDataModel?) : UiState()
    data class Error(val ex: String) : UiState()
}

class UserViewModel (private val authApiService: AuthApiService) : ViewModel() {


    private val _state = MutableStateFlow<UiState>(UiState.Init)
    val state: StateFlow<UiState> get() = _state

    fun login(email: String, password: String,context: Context) {
        val request = LoginRequest(email,password)
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val response = authApiService.login(request)
                if (response.isSuccessful) {
                    val userData = response.body()
                    println("userData::: $userData")
                    PreferencesManager(context = context).saveString(
                        key = PreferencesManager.TOKEN_KEY,
                        userData?.body?.token ?: ""
                    )
                    _state.value = UiState.Success(userData?.body)
                }else{
                    _state.value = UiState.Error("Failure")
                }
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "")
            }
        }
    }
}
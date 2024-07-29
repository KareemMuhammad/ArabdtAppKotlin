package com.example.arabdtappkotlin.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arabdtappkotlin.data.models.UserSavedData
import com.example.arabdtappkotlin.data.models.fromLoginModel
import com.example.arabdtappkotlin.data.repositories.AuthRepositoryImpl
import com.example.arabdtappkotlin.utils.AppState
import com.example.arabdtappkotlin.utils.PreferencesManager
import com.example.arabdtappkotlin.utils.StateValue
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val authRepository: AuthRepositoryImpl) : ViewModel() {


    private val _state = MutableStateFlow<AppState<StateValue>>(AppState.Init())
    val state: StateFlow<AppState<StateValue>> get() = _state

    private val _savedUser = MutableStateFlow<UserSavedData?>(null)
    val savedUser: StateFlow<UserSavedData?> get() = _savedUser

    init {
        getSavedUser()
    }

    fun login(email: String, password: String, context: Context) {
        viewModelScope.launch {
            _state.value = AppState.Loading()
            try {
                val response = authRepository.login(email, password)
                if (response.isSuccessful) {
                    val userData = response.body()
                    println("userData::: $userData")
                    PreferencesManager(context = context).saveString(
                        key = PreferencesManager.TOKEN_KEY,
                        userData?.body?.token ?: ""
                    )
                    _state.value = AppState.Success(StateValue.Success)
                    if (userData == null) return@launch
                    withContext(IO) { // Switch to a background thread (IO dispatcher)
                        val userSavedData = UserSavedData().fromLoginModel(userData.body)
                        _savedUser.value = userSavedData
                        authRepository.saveUser(userSavedData)
                    }
                } else {
                    _state.value = AppState.Error("Failure")
                }
            } catch (e: Exception) {
                println("userViewModel:: ${e.message}")
                _state.value = AppState.Error(e.message ?: "")
            }
        }
    }

    private fun getSavedUser() {
        viewModelScope.launch {
            withContext(IO){
                val user = authRepository.getSavedUser()
                _savedUser.value = user
                println("Saved user:: $user")
            }
        }
    }
}
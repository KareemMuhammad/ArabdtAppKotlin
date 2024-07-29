package com.example.arabdtappkotlin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arabdtappkotlin.App
import com.example.arabdtappkotlin.data.models.AttendanceDataModel
import com.example.arabdtappkotlin.data.models.requests.AttendanceListRequest
import com.example.arabdtappkotlin.data.repositories.AttendanceRepositoryImpl
import com.example.arabdtappkotlin.utils.AppState
import com.example.arabdtappkotlin.utils.StateValue
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AttendanceViewModel(private val attendanceRepositoryImpl: AttendanceRepositoryImpl) :
    ViewModel() {
    private val _state = MutableStateFlow<AppState<StateValue>>(AppState.Init())
    val state: StateFlow<AppState<StateValue>> get() = _state

    private val _attendanceListState = MutableStateFlow<List<AttendanceDataModel>?>(null)
    val attendanceListState: StateFlow<List<AttendanceDataModel>?> get() = _attendanceListState

    private var page = 0

    fun loadAttendanceList(limit: Int = 15) {
        viewModelScope.launch {
            _state.value = AppState.Loading()
            try {
                withContext(IO) {
                    val savedUser = App.appDatabase.userDao().get()
                    val request = AttendanceListRequest(savedUser.token, savedUser.employee)
                    val response =
                        attendanceRepositoryImpl.loadAttendanceList(
                            request,
                            page = page,
                            limit = limit
                        )
                    if (response.isSuccessful) {
                        println("Attendance response:: ${response.body()}")
                        val attendanceList = response.body()?.body
                        _attendanceListState.value = attendanceList
                        _state.value = AppState.Success(StateValue.Success)
                    } else {
                        _state.value = AppState.Error("Failure")
                    }
                }
            } catch (e: Exception) {
                _state.value = AppState.Error(e.message ?: "")
            }
        }
    }
}
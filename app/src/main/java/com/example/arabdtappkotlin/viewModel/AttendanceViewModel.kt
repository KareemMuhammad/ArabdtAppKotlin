package com.example.arabdtappkotlin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arabdtappkotlin.data.models.AttendanceDataModel
import com.example.arabdtappkotlin.data.models.requests.AttendanceListRequest
import com.example.arabdtappkotlin.data.repositories.AttendanceRepositoryImpl
import com.example.arabdtappkotlin.utils.AppState
import com.example.arabdtappkotlin.utils.StateValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AttendanceViewModel(val attendanceRepositoryImpl: AttendanceRepositoryImpl) : ViewModel() {
    private val _state = MutableStateFlow<AppState<StateValue>>(AppState.Init())
    val state: StateFlow<AppState<StateValue>> get() = _state

    private val _attendanceListState = MutableStateFlow<List<AttendanceDataModel>?>(null)
    val attendanceListState: StateFlow<List<AttendanceDataModel>?> get() = _attendanceListState

    fun loadAttendanceList() {
        viewModelScope.launch {
            _state.value = AppState.Loading()
            try {
                val request = AttendanceListRequest("", "")
                val response =
                    attendanceRepositoryImpl.loadAttendanceList(request, page = 0, limit = 2)
                if (response.isSuccessful) {
                    println("Attendance list:: ${response.body()}")
                    val attendanceList = response.body()?.body
                    _attendanceListState.value = attendanceList
                    _state.value = AppState.Success(StateValue.Success)
                } else {
                    _state.value = AppState.Error("Failure")
                }
            } catch (e: Exception) {
                _state.value = AppState.Error(e.message ?: "")
            }
        }
    }
}
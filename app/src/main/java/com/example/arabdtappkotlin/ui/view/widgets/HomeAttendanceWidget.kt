package com.example.arabdtappkotlin.ui.view.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.arabdtappkotlin.utils.AppState
import com.example.arabdtappkotlin.viewModel.AttendanceViewModel

@Composable
fun HomeAttendancePreview(attendanceViewModel: AttendanceViewModel){
    val attendanceList by attendanceViewModel.attendanceListState.collectAsState()
    val state by attendanceViewModel.state.collectAsState()

    if(attendanceList != null) {
        AttendanceListWidget(list = attendanceList!!, loading = state is AppState.Loading) {
            attendanceViewModel.loadAttendanceList()
        }
    }

}
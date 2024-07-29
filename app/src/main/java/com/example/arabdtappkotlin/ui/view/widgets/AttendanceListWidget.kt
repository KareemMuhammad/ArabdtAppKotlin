package com.example.arabdtappkotlin.ui.view.widgets

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.example.arabdtappkotlin.data.models.AttendanceDataModel
import com.example.arabdtappkotlin.ui.components.PaginatedLazyColumn
import com.example.arabdtappkotlin.ui.components.VerticalSpace

@Composable
fun AttendanceListWidget(
    list: List<AttendanceDataModel>,
    loading: Boolean,
    loadMore: () -> Unit
) {
    PaginatedLazyColumn(
        loading = loading,
        items = list,
        itemKey = { attendance: AttendanceDataModel -> attendance.date },
        itemContent = { attendance: AttendanceDataModel ->
            AttendanceWidget(attendance)
            VerticalSpace(height = 8)
        },
        loadingItem = { CircularProgressIndicator() },
        loadMore = loadMore
    )
}
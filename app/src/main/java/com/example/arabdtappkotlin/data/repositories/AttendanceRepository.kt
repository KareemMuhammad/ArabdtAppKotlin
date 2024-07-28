package com.example.arabdtappkotlin.data.repositories

import com.example.arabdtappkotlin.data.models.AttendanceDataModel
import com.example.arabdtappkotlin.data.models.BaseResponse
import com.example.arabdtappkotlin.data.models.requests.AttendanceListRequest
import retrofit2.Response

interface AttendanceRepository {
    suspend fun loadAttendanceList(
        attendanceBody: AttendanceListRequest,
        page: Int,
        limit: Int
    ): Response<BaseResponse<List<AttendanceDataModel>>>
}
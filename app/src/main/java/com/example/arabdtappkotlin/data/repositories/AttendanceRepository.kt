package com.example.arabdtappkotlin.data.repositories

import com.example.arabdtappkotlin.data.models.AttendanceDataModel
import com.example.arabdtappkotlin.data.models.BaseResponse
import retrofit2.Response

interface AttendanceRepository {
    suspend fun loadAttendanceList(): Response<BaseResponse<List<AttendanceDataModel>>>
}
package com.example.arabdtappkotlin.data.repositories

import com.example.arabdtappkotlin.data.models.AttendanceDataModel
import com.example.arabdtappkotlin.data.models.BaseResponse
import com.example.arabdtappkotlin.data.models.requests.AttendanceListRequest
import com.example.arabdtappkotlin.data.source.remote.AttendanceApiService
import retrofit2.Response

class AttendanceRepositoryImpl(val apiService: AttendanceApiService) : AttendanceRepository {
    override suspend fun loadAttendanceList(
        attendanceBody: AttendanceListRequest,
        page: Int,
        limit: Int
    ): Response<BaseResponse<List<AttendanceDataModel>>> {
        val response = apiService.getAttendanceList(attendanceBody, page, limit)
        return response
    }
}
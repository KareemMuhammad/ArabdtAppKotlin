package com.example.arabdtappkotlin.data.source.remote

import com.example.arabdtappkotlin.data.models.AttendanceDataModel
import com.example.arabdtappkotlin.data.models.BaseResponse
import com.example.arabdtappkotlin.data.models.requests.AttendanceListRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface AttendanceApiService {
    @GET(ApiConstants.ATTENDANCE_LIST)
    suspend fun getAttendanceList(
        @Body attendanceBody: AttendanceListRequest,
        @Query("limit_start") page: Int,
        @Query("limit_page_length") limit: Int
    ): Response<BaseResponse<List<AttendanceDataModel>>>
}
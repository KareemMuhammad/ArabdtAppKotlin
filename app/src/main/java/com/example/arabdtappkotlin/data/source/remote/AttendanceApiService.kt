package com.example.arabdtappkotlin.data.source.remote

import com.example.arabdtappkotlin.data.models.AttendanceDataModel
import com.example.arabdtappkotlin.data.models.BaseResponse
import retrofit2.Response
import retrofit2.http.GET

interface AttendanceApiService {
    @GET(ApiConstants.ATTENDANCE_LIST)
    suspend fun getAttendanceList(): Response<BaseResponse<List<AttendanceDataModel>>>
}
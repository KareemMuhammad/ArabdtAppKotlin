package com.example.arabdtappkotlin.data.source.remote

object ApiConstants {
    const val BASE_URL: String = "https://netst-api.arabdt.com/"
    const val IMAGE_BASE_URL: String = "https://test-erpapi.arabdt.com/"

    // auth
    const val LOGIN_PATH = "auth/login"
    const val LOGOUT_PATH = "auth/logout"
    const val USER_PROFILE_PATH = "profile/profile"

    // attendance
     const val EMPLOYEE_CHECK_IN = "checkin-out/employeeCheckin"
     const val ATTENDANCE_LIST = "attendence/daily_attendance"
}
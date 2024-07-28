package com.example.arabdtappkotlin.data.models

import com.google.gson.annotations.SerializedName


data class AttendanceDataModel(
    val date: String,
    @SerializedName("first_in")
    val checkInTime: String?,
    @SerializedName("last_out")
    val checkoutTime: String?,
    val duration: String?
)

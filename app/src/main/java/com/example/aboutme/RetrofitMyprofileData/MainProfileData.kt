package com.example.aboutme.RetrofitMyprofileData

import com.google.gson.annotations.SerializedName

data class MainProfileData(
    @SerializedName("code")
    val code: String,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: Result
){
    fun getTotalMyProfile(): Int {
        return result.totalMyprofile
    }
}


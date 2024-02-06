package com.example.aboutme.RetrofitMyprofileData

import com.google.gson.annotations.SerializedName

data class Myprofile(
    @SerializedName("front_features")
    val frontFeatures: List<FrontFeature>,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("profile_id")
    val profileId: Int,
    @SerializedName("profile_img_url")
    val profileImgUrl: Any?,
    @SerializedName("serial_number")
    val serialNumber: Int
)
package com.yrgv.flopp.data.network.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("line1") val line1: String,
    @SerializedName("line2") val line2: String?,
    @SerializedName("city") val city: String,
    @SerializedName("province") val province: String,
    @SerializedName("country") val country: String
)
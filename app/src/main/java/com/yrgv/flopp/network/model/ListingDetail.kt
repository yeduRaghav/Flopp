package com.yrgv.flopp.network.model

import com.google.gson.annotations.SerializedName

data class ListingDetail(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("location") val location: Location,
    @SerializedName("price") val price: Float,
    @SerializedName("image") val image: String,
    @SerializedName("lenderName") val lenderName: String,
    @SerializedName("model") val model: String,
    @SerializedName("rating") val rating: Float
)
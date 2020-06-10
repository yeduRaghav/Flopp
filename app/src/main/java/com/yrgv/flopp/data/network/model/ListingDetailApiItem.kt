package com.yrgv.flopp.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Defines a detailed listing in api
 * */
data class ListingDetailApiItem(
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
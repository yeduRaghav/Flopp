package com.yrgv.flopp.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Defines a Listing in the api.
 * Name suffixed with `ApiItem` to avoid confusion since there are other `Listing` classes.
 * */
data class ListingApiItem(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("location") val location: Location,
    @SerializedName("price") val price: Float,
    @SerializedName("image") val image: String
)

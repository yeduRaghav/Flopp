package com.yrgv.flopp.util

import com.yrgv.flopp.data.db.listings.ListingEntity
import com.yrgv.flopp.data.network.model.ListingApiItem
import com.yrgv.flopp.data.network.model.Location

/**
 * Holds extension functions for models
 */

fun List<ListingApiItem>.toListingEntities(): List<ListingEntity> {
    return mapTo(arrayListOf()) {
        it.toListingEntity()
    }
}

/**
 * Converts Listing from api model response to Local db listing model
 * */
fun ListingApiItem.toListingEntity(): ListingEntity {
    return ListingEntity(
        id = id,
        title = title,
        description = description,
        location = location.toLocalFormat(),
        price = price,
        image = image
    )
}

/**
 * Converts location into the app's local format
 * */
fun Location.toLocalFormat(): String {
    return StringBuilder().apply {
        append(line1)
        append(", ")
        line2?.let {
            if (it.isNotBlank()) {
                append(it)
                append(", ")
            }
        }
        append(city)
    }.toString()
}



package com.yrgv.flopp.util

import com.yrgv.flopp.data.db.listings.ListingEntity
import com.yrgv.flopp.data.network.model.ListingApiItem
import com.yrgv.flopp.data.network.model.Location
import com.yrgv.flopp.ui.main.ListingListItem

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

fun List<ListingApiItem>.toListItems(): List<ListingListItem> {
    return mapTo(arrayListOf()) {
        it.toListItem()
    }
}

fun ListingApiItem.toListItem(): ListingListItem {
    return ListingListItem(
        id = id,
        title = "$id $title",
        price = price.toLocalCurrencyFormat(),
        location = location.toLocalFormat(),
        image = image
    )
}

fun List<ListingEntity>.toListItem(): List<ListingListItem> {
    return mapTo(arrayListOf()) {
        it.toListItem()
    }
}

fun ListingEntity.toListItem(): ListingListItem {
    return ListingListItem(
        id = id,
        title = "$id $title",
        price = price.toLocalCurrencyFormat(),
        location = location,
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

/**
 * Converts the float to local currency display format
 * */
fun Float.toLocalCurrencyFormat(): String {
    return "$ $this"
}
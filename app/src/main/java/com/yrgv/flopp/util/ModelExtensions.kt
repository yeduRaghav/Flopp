package com.yrgv.flopp.util

import com.yrgv.flopp.data.db.detail.ListingDetailEntity
import com.yrgv.flopp.data.db.listings.ListingEntity
import com.yrgv.flopp.data.network.model.ListingApiItem
import com.yrgv.flopp.data.network.model.ListingDetailApiItem
import com.yrgv.flopp.data.network.model.Location
import com.yrgv.flopp.ui.detail.DetailedListing
import com.yrgv.flopp.ui.main.ListingListItem

/**
 * Holds extension functions for models
 */

/**
 * Converts api listing model to db model
 * */
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

/**
 * Converts api listing model to local model
 * */
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

/**
 * Converts db listing object to local model
 * */
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

/**
 * Converts detailed api listing to db model
 */
fun ListingDetailApiItem.toListingDetailEntity(): ListingDetailEntity {
    return ListingDetailEntity(
        id = id,
        title = title,
        description = description,
        location = location.toLocalFormat(),
        price = price,
        image = image,
        lenderName = lenderName,
        model = model,
        rating = rating
    )
}

/**
 * Converts detailed api listing to local model
 * */
fun ListingDetailApiItem.toLocalModel():DetailedListing {
    return DetailedListing(
        id = id,
        title = title,
        description = description,
        location = location.toLocalFormat(),
        price = price.toLocalCurrencyFormat(),
        image = image,
        lenderName = lenderName,
        model = model,
        rating = rating
    )
}

/**
 * Converts detailed db listing to local model
 * */
fun ListingDetailEntity.toLocalModel():DetailedListing {
    return DetailedListing(
        id = id,
        title = title,
        description = description,
        location = location,
        price = price.toLocalCurrencyFormat(),
        image = image,
        lenderName = lenderName,
        model = model,
        rating = rating
    )
}
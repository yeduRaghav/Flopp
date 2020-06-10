package com.yrgv.flopp.data.db.listings

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table that holds listings.
 * Name suffixed with `Entity` to avoid confusion since there are other `Listing` classes
 */
@Entity
data class ListingEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val description: String,
    val location: String,
    val price: Float,
    val image: String
)
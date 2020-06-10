package com.yrgv.flopp.data.db.detail

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table that holds listing details.
 * Name suffixed with `Entity` to avoid confusion since there are other `ListingDetail` classes
 */
@Entity
data class ListingDetailEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val description: String,
    val location: String,
    val price: Float,
    val image: String,
    val lenderName: String,
    val model: String,
    val rating: Float
)
package com.yrgv.flopp.ui.detail

/**
 * Localized  model for a detailed listing
 */
data class DetailedListing(
    val id: Long,
    val title: String,
    val description: String,
    val location: String,
    val price: String,
    val image: String,
    val lenderName: String,
    val model: String,
    val rating: Float
)
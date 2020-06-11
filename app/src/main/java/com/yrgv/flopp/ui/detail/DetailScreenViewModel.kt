package com.yrgv.flopp.ui.detail

import androidx.lifecycle.ViewModel
import com.yrgv.flopp.data.ListingsRepository

/**
 * ViewModel for the detail screen.
 */
class DetailScreenViewModel(
    private val repository: ListingsRepository
) : ViewModel() {

    private var listingId: Long? = null

    fun setData(listingId: Long) {
        this.listingId = listingId

    }


//    private fun fetchListingDetail() {
//        listingId?.let {
//            repository.fetchListingDetailFromApi(it,)
//        }
//    }


}
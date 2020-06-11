package com.yrgv.flopp.data.repo

import com.yrgv.flopp.data.db.detail.ListingDetailEntity
import com.yrgv.flopp.data.db.listings.ListingEntity
import com.yrgv.flopp.data.network.ApiError
import com.yrgv.flopp.data.network.model.ListingApiItem
import com.yrgv.flopp.data.network.model.ListingDetailApiItem
import com.yrgv.flopp.util.Either
import io.reactivex.Single
import io.reactivex.SingleObserver

/**
 * Defines the behaviours of the repository
 */
interface ListingsRepository {

    fun fetchListingsFromApi(
        page: Int,
        observer: SingleObserver<Either<ApiError, List<ListingApiItem>>>
    )

    fun fetchLocalListings(): Single<List<ListingEntity>>

    fun fetchListingDetailFromApi(
        listingId: Long,
        observer: SingleObserver<Either<ApiError, ListingDetailApiItem>>
    )

    fun fetchListingDetailFromDb(listingId: Long): Single<Either<Unit, ListingDetailEntity>>

}
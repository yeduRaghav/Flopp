package com.yrgv.flopp.ui.main

import androidx.lifecycle.ViewModel
import com.yrgv.flopp.network.ApiError
import com.yrgv.flopp.network.GetDetailsEndpoint
import com.yrgv.flopp.network.GetPostingsListEndpoint
import com.yrgv.flopp.network.model.Listing
import com.yrgv.flopp.network.model.ListingDetail
import com.yrgv.flopp.util.Either
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * ViewModel for the main screen.
 */
class MainScreenViewModel(listingsEndpoints: GetPostingsListEndpoint, detailsEndpoint: GetDetailsEndpoint) : ViewModel() {

    init {
        listingsEndpoints.execute(1, object : SingleObserver<Either<ApiError, List<Listing>>> {
            override fun onSubscribe(d: Disposable) {}
            override fun onError(throwable: Throwable) {}
            override fun onSuccess(response: Either<ApiError, List<Listing>>) {
                when (response) {
                    is Either.Error -> response.error
                    is Either.Value -> response.value
                }
            }
        })

        detailsEndpoint.execute(1L, object:SingleObserver<Either<ApiError, ListingDetail>> {
            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {}
            override fun onSuccess(response: Either<ApiError, ListingDetail>) {
                when (response) {
                    is Either.Error -> response.error
                    is Either.Value -> response.value
                }
            }
        })
    }
    
}
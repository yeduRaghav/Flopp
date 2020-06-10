package com.yrgv.flopp.ui.main

import androidx.lifecycle.ViewModel
import com.yrgv.flopp.data.network.ApiError
import com.yrgv.flopp.data.network.GetDetailsEndpoint
import com.yrgv.flopp.data.network.GetPostingsListEndpoint
import com.yrgv.flopp.data.network.model.ListingApiItem
import com.yrgv.flopp.data.network.model.ListingDetailApiItem
import com.yrgv.flopp.util.Either
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * ViewModel for the main screen.
 */
class MainScreenViewModel(listingsEndpoints: GetPostingsListEndpoint, detailsEndpoint: GetDetailsEndpoint) : ViewModel() {

    init {
        listingsEndpoints.execute(
            1,
            object : SingleObserver<Either<ApiError, List<ListingApiItem>>> {
            override fun onSubscribe(d: Disposable) {}
            override fun onError(throwable: Throwable) {}
                override fun onSuccess(response: Either<ApiError, List<ListingApiItem>>) {
                when (response) {
                    is Either.Error -> response.error
                    is Either.Value -> response.value
                }
            }
        })

        detailsEndpoint.execute(
            1L,
            object : SingleObserver<Either<ApiError, ListingDetailApiItem>> {
            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {}
                override fun onSuccess(response: Either<ApiError, ListingDetailApiItem>) {
                when (response) {
                    is Either.Error -> response.error
                    is Either.Value -> response.value
                }
            }
        })
    }
    
}
package com.yrgv.flopp.ui.main

import androidx.lifecycle.ViewModel
import com.yrgv.flopp.data.ListingsRepository
import com.yrgv.flopp.data.network.ApiError
import com.yrgv.flopp.data.network.model.ListingApiItem
import com.yrgv.flopp.util.Either
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * ViewModel for the main screen.
 */
class MainScreenViewModel(
    private val repository: ListingsRepository
) : ViewModel() {

    init {
        getFromApi(1)
    }

    private fun getFromApi(page: Int) {
        if (page > 2) {
            return
        }
        repository.fetchListingsFromApi(
            page,
            object : SingleObserver<Either<ApiError, List<ListingApiItem>>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onError(e: Throwable) {}
                override fun onSuccess(response: Either<ApiError, List<ListingApiItem>>) {
                    when (response) {
                        is Either.Value -> {
                            getFromApi(page = page + 1)
                        }
                        is Either.Error -> {
                            repository.fetchLocalListings().subscribe { entities ->
                                entities.size
                            }
                        }
                    }
                }
            })
    }
}
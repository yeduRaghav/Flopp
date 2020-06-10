package com.yrgv.flopp.data.network

import android.annotation.SuppressLint
import com.yrgv.flopp.data.network.model.ListingApiItem
import com.yrgv.flopp.util.Either
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Endpoint returns a page of postings
 */
class GetPostingsListEndpoint constructor(private val api: ApiService) {

    @SuppressLint("CheckResult")
    fun execute(page: Int, observer: SingleObserver<Either<ApiError, List<ListingApiItem>>>) {
        Single.just(api.getListings(page))
            .flatMap { return@flatMap Single.just(it.execute()) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe { apiResponse, exception ->
                exception?.let {
                    val localException = ApiError.getLocalizedErrorResponse(it)
                    observer.onSuccess(Either.error(localException))
                    return@subscribe
                }
                apiResponse.body()?.let {
                    observer.onSuccess(Either.value(it))
                } ?: kotlin.run {
                    observer.onSuccess(
                        Either.error(
                            ApiError.getLocalizedErrorResponse(
                                apiResponse.code(),
                                apiResponse.errorBody()?.string()
                            )
                        )
                    )
                }
            }
    }

}
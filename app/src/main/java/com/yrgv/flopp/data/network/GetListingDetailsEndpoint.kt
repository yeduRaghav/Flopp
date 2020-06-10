package com.yrgv.flopp.data.network

import android.annotation.SuppressLint
import com.yrgv.flopp.data.network.model.ListingDetailApiItem
import com.yrgv.flopp.util.Either
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.schedulers.Schedulers

/**
 * Gets detailed response for a listing
 */
class GetListingDetailsEndpoint constructor(private val api: ApiService) {

    @SuppressLint("CheckResult")
    fun execute(id: Long, observer: SingleObserver<Either<ApiError, ListingDetailApiItem>>) {
        Single.just(api.getDetail(id))
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
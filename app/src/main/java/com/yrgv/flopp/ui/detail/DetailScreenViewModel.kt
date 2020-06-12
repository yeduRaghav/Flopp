package com.yrgv.flopp.ui.detail

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yrgv.flopp.data.network.ApiError
import com.yrgv.flopp.data.network.model.ListingDetailApiItem
import com.yrgv.flopp.data.repo.ListingsRepository
import com.yrgv.flopp.util.Either
import com.yrgv.flopp.util.toLocalModel
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * ViewModel for the detail screen.
 */
class DetailScreenViewModel(
    private val repository: ListingsRepository
) : ViewModel() {
    private var listingId: Long? = null
    private val viewStateLiveData = MutableLiveData<ViewState>()
    private val listingLiveData = MutableLiveData<DetailedListing>()

    init {
        viewStateLiveData.postValue(ViewState.Loading)
    }

    fun getViewState(): LiveData<ViewState> {
        return viewStateLiveData
    }

    fun getListing(): LiveData<DetailedListing> {
        return listingLiveData
    }

    fun setData(listingId: Long) {
        this.listingId = listingId
        reload()
    }

    fun onTryAgainClicked() {
        reload()
    }

    private fun reload() {
        listingId?.let {
            fetchListingDetailFromApi(it)
        }
    }

    private fun fetchListingDetailFromApi(listingId: Long) {
        repository.fetchListingDetailFromApi(
            listingId,
            object : SingleObserver<Either<ApiError, ListingDetailApiItem>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onError(e: Throwable) {}
                override fun onSuccess(response: Either<ApiError, ListingDetailApiItem>) {
                    when (response) {
                        is Either.Error -> {
                            fetchListingDetailFromDb()
                        }
                        is Either.Value -> {
                            handleListingFromApi(response.value)
                        }
                    }
                }
            }
        )
    }


    @SuppressLint("CheckResult")
    private fun handleListingFromApi(apiItem: ListingDetailApiItem) {
        Single.just(apiItem)
            .observeOn(Schedulers.io())
            .flatMap {
                Single.just(apiItem.toLocalModel())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { localListing ->
                listingLiveData.postValue(localListing)
                viewStateLiveData.postValue(ViewState.LoadingSuccess)
            }
    }

    private fun fetchListingDetailFromDb() {
        listingId?.let { id ->
            repository.fetchListingDetailFromDb(id)
                .observeOn(Schedulers.io())
                .flatMap { dbResult ->
                    if (dbResult is Either.Value) {
                        Single.just(Either.value(dbResult.value.toLocalModel()))
                    } else {
                        Single.just(Either.error(Unit))
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { localizedResult ->
                    handleResultFromDb(localizedResult)
                }
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun handleResultFromDb(result: Either<Unit, DetailedListing>) {
        when (result) {
            is Either.Error -> {
                viewStateLiveData.postValue(ViewState.LoadingFailed)
            }
            is Either.Value -> {
                listingLiveData.postValue(result.value)
                viewStateLiveData.postValue(ViewState.LoadingSuccess)
            }
        }
    }

    sealed class ViewState {
        object Loading : ViewState()
        object LoadingSuccess : ViewState()
        object LoadingFailed : ViewState()
    }
}
package com.yrgv.flopp.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yrgv.flopp.data.network.ApiError
import com.yrgv.flopp.data.network.model.ListingApiItem
import com.yrgv.flopp.data.repo.ListingsRepository
import com.yrgv.flopp.util.Either
import com.yrgv.flopp.util.toListItem
import com.yrgv.flopp.util.toListItems
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * ViewModel for the main screen.
 */
class MainScreenViewModel(
    private val repository: ListingsRepository
) : ViewModel() {

    private val dataLoadingHelper = DataLoadingHelper()
    private var isCurrentlyDisplayingOfflineData = false
    private val listingsListItemsLiveData = MutableLiveData<List<ListingListItem>>()
    private val listDataStateLiveData = MutableLiveData<ListDataState>()

    init {
        attemptToLoadNextPage()
    }

    fun getListings(): LiveData<List<ListingListItem>> {
        return listingsListItemsLiveData
    }

    fun getListDataState(): LiveData<ListDataState> {
        return listDataStateLiveData
    }

    fun onListBottomReached() {
        attemptToLoadNextPage()
    }

    fun onTryAgainClicked() {
        reload()
    }

    private fun reload() {
        isCurrentlyDisplayingOfflineData = false
        dataLoadingHelper.reset()
        attemptToLoadNextPage()
    }

    private fun attemptToLoadNextPage() {
        if (!dataLoadingHelper.canLoadNextPage() || isCurrentlyDisplayingOfflineData) return
        dataLoadingHelper.startLoading()
        fetchFromApi(dataLoadingHelper.getCurrentPage() + 1)
    }

    private fun fetchFromApi(page: Int) {
        if (page == 1) {
            listDataStateLiveData.postValue(ListDataState.FirstPageLoading)
        }

        val responseObserver = object : SingleObserver<Either<ApiError, List<ListingApiItem>>> {
            override fun onSubscribe(d: Disposable) {}
            override fun onError(e: Throwable) {}
            override fun onSuccess(response: Either<ApiError, List<ListingApiItem>>) {
                when (response) {
                    is Either.Value -> {
                        handleApiListingsResponse(page, response.value)
                    }
                    is Either.Error -> {
                        if (page == 1) {
                            fetchLocalListings()
                        } else {
                            listDataStateLiveData.postValue(ListDataState.NextPageLoadFailed)
                            dataLoadingHelper.stopLoading()
                        }
                    }
                }
            }
        }
        repository.fetchListingsFromApi(page, responseObserver)
    }

    @SuppressLint("CheckResult")
    private fun handleApiListingsResponse(page: Int, listingsFromApi: List<ListingApiItem>) {
        Single.just(listingsFromApi)
            .observeOn(Schedulers.io())
            .flatMap {
                Single.just(it.toListItems())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { localizedListings ->
                if (page == 1) {
                    listDataStateLiveData.postValue(ListDataState.FirstPageLoaded)
                }
                updateList(localizedListings)
                dataLoadingHelper.loadingComplete()
            }
    }

    @SuppressLint("CheckResult")
    private fun fetchLocalListings() {
        repository.fetchLocalListings()
            .observeOn(Schedulers.io())
            .flatMap {
                Single.just(it.toListItem())
            }
            .subscribe { localizedListings ->
                if (localizedListings.isNullOrEmpty()) {
                    listDataStateLiveData.postValue(ListDataState.FirstPageLoadFailed)
                } else {
                    listDataStateLiveData.postValue(ListDataState.FirstPageLoaded)
                    updateList(localizedListings)
                    isCurrentlyDisplayingOfflineData = true
                }
                dataLoadingHelper.reset()
            }
    }

    @SuppressLint("CheckResult")
    private fun updateList(newListingItems: List<ListingListItem>) {
        Single.just(newListingItems)
            .observeOn(Schedulers.io())
            .flatMap {
                val newValue = mutableListOf<ListingListItem>()
                listingsListItemsLiveData.value?.let { currentItems ->
                    newValue.addAll(currentItems)
                }
                newValue.addAll(newListingItems)
                Single.just(newValue)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { newValues ->
                listingsListItemsLiveData.postValue(newValues)
            }
    }

    /**
     * Describes the state of Listings view
     **/
    sealed class ListDataState {
        object FirstPageLoaded : ListDataState()
        object FirstPageLoading : ListDataState()
        object FirstPageLoadFailed : ListDataState()
        object NextPageLoadFailed : ListDataState()
    }
}

/**
 * A simple class to help manage data loading.
 * */
data class DataLoadingHelper(
    private var isInFlight: Boolean = false,
    private var currentPage: Int = 0
) {
    //Only load 2 pages for this app, since that's all the static data available
    private val maxPageCount = 2
    fun canLoadNextPage(): Boolean {
        return (!isInFlight && (currentPage < maxPageCount))
    }

    fun startLoading() {
        isInFlight = true
    }

    fun stopLoading() {
        isInFlight = false
    }

    fun loadingComplete() {
        isInFlight = false
        currentPage++
    }

    fun getCurrentPage(): Int {
        return currentPage
    }

    fun reset() {
        currentPage = 0
        isInFlight = false
    }

}

package com.yrgv.flopp.data.repo

import android.app.Application
import com.yrgv.flopp.data.db.ListingDatabase
import com.yrgv.flopp.data.db.detail.ListingDetailEntity
import com.yrgv.flopp.data.db.listings.ListingEntity
import com.yrgv.flopp.data.network.ApiError
import com.yrgv.flopp.data.network.ApiService
import com.yrgv.flopp.data.network.GetListingDetailsEndpoint
import com.yrgv.flopp.data.network.GetListingsEndpoint
import com.yrgv.flopp.data.network.model.ListingApiItem
import com.yrgv.flopp.data.network.model.ListingDetailApiItem
import com.yrgv.flopp.util.Either
import com.yrgv.flopp.util.toListingDetailEntity
import com.yrgv.flopp.util.toListingEntities
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * This repo is responsible for providing data
 */
class DefaultListingsRepository private constructor(
    private val listingDatabase: ListingDatabase,
    private val getListingsEndpoint: GetListingsEndpoint,
    private val getListingDetailsEndpoint: GetListingDetailsEndpoint
) : ListingsRepository {

    companion object {
        private lateinit var repositoryInstance: DefaultListingsRepository

        fun getInstance(application: Application): DefaultListingsRepository {
            if (!Companion::repositoryInstance.isInitialized) {
                repositoryInstance =
                    DefaultListingsRepository(
                        ListingDatabase.getInstance(application),
                        GetListingsEndpoint(ApiService.getInstance(application)),
                        GetListingDetailsEndpoint(ApiService.getInstance(application))
                    )
            }
            return repositoryInstance
        }
    }

    /**
     * Fetches listings from api and add them to the repo if it is the first page
     * */
    override fun fetchListingsFromApi(
        page: Int,
        observer: SingleObserver<Either<ApiError, List<ListingApiItem>>>
    ) {
        getListingsEndpoint.execute(
            page,
            object : SingleObserver<Either<ApiError, List<ListingApiItem>>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onError(e: Throwable) {}
                override fun onSuccess(apiResponse: Either<ApiError, List<ListingApiItem>>) {
                    if (apiResponse is Either.Value) {
                        updateListingsInDb(page, apiResponse.value.toListingEntities())
                    }
                    observer.onSuccess(apiResponse)
                }
            }
        )
    }


    /**
     * Adds the listings to db if there are any.
     * Clears entries in Listing table if its the first page.
     * @param page The page number of the current list of entities.
     * @param listings The list of entities to be added to the db.
     * */
    private fun updateListingsInDb(page: Int, listings: List<ListingEntity>) {
        if (listings.isEmpty()) return
        listingDatabase.listingEntityDao().run {
            if (page == 1) {
                removeAll()
            }
            insert(listings)
        }
    }

    /**
     * Returns all the listings stored locally
     * */
    override fun fetchLocalListings(): Single<List<ListingEntity>> {
        return Single.just(listingDatabase.listingEntityDao())
            .observeOn(Schedulers.io())
            .flatMap {
                it.getAll()
            }
    }


    /**
     * Attempts to fetch listing detail object from Api and if the response is valid, it is saved in DB.
     * */
    override fun fetchListingDetailFromApi(
        listingId: Long,
        observer: SingleObserver<Either<ApiError, ListingDetailApiItem>>
    ) {
        getListingDetailsEndpoint.execute(
            listingId,
            object : SingleObserver<Either<ApiError, ListingDetailApiItem>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onError(e: Throwable) {}
                override fun onSuccess(response: Either<ApiError, ListingDetailApiItem>) {
                    if (response is Either.Value) {
                        listingDatabase.listDetailDao().insert(response.value.toListingDetailEntity())
                    }
                    observer.onSuccess(response)
                }
            })
    }

    /**
     * Attempts fetch the listing detail object from Db
     * */
    override fun fetchListingDetailFromDb(listingId: Long): Single<Either<Unit, ListingDetailEntity>> {
        val hardCodedId = 1L //only this id is available in table
        return Single.just(listingDatabase.listDetailDao())
            .observeOn(Schedulers.io())
            .flatMap { dao ->
                val result = dao.get(hardCodedId)?.let {
                    Either.value(it)
                } ?: Either.error(Unit)
                Single.just(result)
            }
    }

}


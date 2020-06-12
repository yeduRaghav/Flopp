package com.yrgv.flopp.ui.main

import org.junit.Test

class MainScreenViewModelTest {


    @Test
    fun cannotLoadNextPageIfLocalDataIsDisplayed() {
        /**
         * todo: this will require some refactor and isCurrentlyDisplayingOfflineData propert should be moved into
         * DataLoadingHelper
         */
    }

    @Test
    fun getCollectionForListingItemsLiveDataAppendsForNewPage() {
        /**
         * todo: ensure if values exist in listingsListItemsLiveData, the new values are appending on to the existing collection,
         * similarly if no values exist, only the new values are returned
         * */
    }

    @Test
    fun fetchFromApiForFirstPageInvokesFirstPageLoadingViewState() {
       /**
        * ensure fetchFromApi(page =1) posts ListDataState.FirstPageLoading into listDataStateLiveData
        * */
    }

    @Test
    fun firstPageApiCallFailureAttemptsToFetchDataFromDb() {
        /**
         *
         * see onApiListingsErrorResponse
         */

    }




}
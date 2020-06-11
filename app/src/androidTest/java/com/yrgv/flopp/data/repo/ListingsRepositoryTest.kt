package com.yrgv.flopp.data.repo

import org.junit.Assert.*
import org.junit.Test

/**
 *  Test the repo implementation
 */
class ListingsRepositoryTest {

    @Test
    fun updateListingsInDbInvokedForApiSuccessValidResponse() {
        //todo: Assert updateListingsInDb() is invoked if apiResponse is Either.Value
    }

    @Test
    fun updateListingsInDbNotInvokedForApiSuccessErrorResponse() {
        //todo: Assert updateListingsInDb() is NOT invoked if apiResponse is Either.Error
    }

    @Test
    fun updateListingsInDbInvokesRemovesAllTableEntriesForFirstPage() {
        /**
         * todo: Assert if first page, ListingEntityDao.removeAll() is invoked
         * */
    }

    @Test
    fun updateListingsInDbDoesNotInvokeRemovesAllTableEntriesForNonFirstPage() {
        /**
         * todo: Assert if NOT first page, ListingEntityDao.removeAll() is NOT invoked
         * */
    }

    @Test
    fun updateListingsInDbReturnsFalseForEmptyList() {
        //todo:
    }

    @Test
    fun updateListingsInDbReturnsTrueForNonEmptyList() {
        //todo:
    }

    @Test
    fun detailApiCallResponseInvokesTableInsertionForValue() {
        /**
         * Assert ListingDetailDao.insert() is invoked if the api response is Either.Value
         * */
    }

    @Test
    fun detailApiCallResponseDoesNotInvokesTableInsertionForValue() {
        /**
         * Assert ListingDetailDao.insert() is NOT invoked if the api response is NOT Either.Value
         * */
    }
}

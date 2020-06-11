package com.yrgv.flopp.data.db.listings

import org.junit.Test

/**
 * Test the DAO's behaviours
 */
class ListingEntityDaoTest {

    @Test
    fun insertAddsAllEntriesIntoEmptyTable() {
        //todo:
    }

    @Test
    fun insertAppendsNewUniqueEntriesIntoNonEmptyTable() {
        /**
         * Assert when new values with unique primaryKeys(no conflict) are passed in,
         * the table adds them to the table without disturbing existing entries
         * */
    }

    @Test
    fun insertReplacesAllEntriesWithSamePrimaryKey() {
        /**
         *  Assert when new values with primaryKeys that match the primaryKeys of
         *  existing entries are passed in, existing entries are replaced
         *  with new entries with matching primaryKey
         * */
    }

    @Test
    fun getAllReturnsEmptyListFromEmptyTable() {
        //todo:
    }

    @Test
    fun getAllReturnsListOfAllEntriesFromNonEmptyTable() {
        //todo:
    }

    @Test
    fun removeAllDeletesAllEntriesFromNonEmptyTable() {
        //todo:
    }

}
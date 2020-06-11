package com.yrgv.flopp.data.db.detail

import org.junit.Test

class ListingEntityDaoTest {

    @Test
    fun insertAddsEntityWithUniqueIdIntoTable() {
        /**
         * Assert entries size in table is correct after insertion
         * Further more tests can be added where all elements of entries are same with existing one entry,
         * except for the PrimaryKey and the previous entry is not replaced.
         * */
    }

    @Test
    fun insertReplacesEntitySamePrimaryKey() {
        /**
         * Assert and an existing entry with same primary key gets replaced by new entry
         * */
    }

    @Test
    fun getReturnsEntryOfMatchingId() {
        //todo:
    }

    @Test
    fun getReturnsNullForIdNotPresentInTable() {
        //todo:
    }
}
package com.yrgv.flopp.data.db.detail

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

/**
 * DAO for ListingDetailEntity table
 */
@Dao
interface ListingDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(listingDetailEntity: ListingDetailEntity)

    @Query("SELECT * FROM listingdetailentity where id == :id")
    fun get(id: Long): ListingDetailEntity?
}
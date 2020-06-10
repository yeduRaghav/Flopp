package com.yrgv.flopp.data.db.listings

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

/**
 * DAO for ListingEntity table
 */
@Dao
interface ListingEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(listingEntities: List<ListingEntity>)

    @Query("SELECT * FROM listingentity")
    fun getAll(): Single<List<ListingEntity>>

    @Query("DELETE FROM listingentity")
    fun removeAll()
}
package com.yrgv.flopp.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yrgv.flopp.data.db.detail.ListingDetailDao
import com.yrgv.flopp.data.db.detail.ListingDetailEntity
import com.yrgv.flopp.data.db.listings.ListingEntity
import com.yrgv.flopp.data.db.listings.ListingEntityDao

/**
 * This is the Database class for the application
 */
@Database(entities = [ListingEntity::class, ListingDetailEntity::class], version = 1)
abstract class ListingDatabase : RoomDatabase() {

    companion object {
        private val lock = Any()
        private const val DB_NAME = "Trivia.db"
        private lateinit var instance: ListingDatabase

        /**
         * Invoke this function to acquire an instance of the database
         **/
        fun getInstance(application: Application): ListingDatabase {
            synchronized(lock) {
                if (!::instance.isInitialized) {
                    instance = createInstance(application)
                }
                return instance
            }
        }

        /**
         * Creates an instance of the database
         **/
        private fun createInstance(application: Application): ListingDatabase {
            return Room.databaseBuilder(application, ListingDatabase::class.java, DB_NAME).build()
        }
    }

    abstract fun listingEntityDao(): ListingEntityDao

    abstract fun listDetailDao(): ListingDetailDao
}
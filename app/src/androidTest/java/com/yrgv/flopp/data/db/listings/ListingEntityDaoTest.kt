package com.yrgv.flopp.data.db.listings

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.yrgv.flopp.data.db.ListingDatabase
import org.junit.*
import org.junit.runner.RunWith

/**
 * Test the DAO's behaviours
 */
@RunWith(AndroidJUnit4::class)
class ListingEntityDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: ListingDatabase
    private lateinit var daoSut: ListingEntityDao

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            ListingDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        daoSut = database.listingEntityDao()
    }

    @After
    fun closeDb() {
        database.close()
    }


    @Test
    fun insertAddsAllEntriesIntoEmptyTable() {
        daoSut.removeAll()
        val entries = mutableListOf<ListingEntity>().apply {
            add(ListingEntity(1, "title", "description", "location", 2f, "image"))
            add(ListingEntity(2, "title", "description", "location", 2f, "image"))
            add(ListingEntity(3, "title", "description", "location", 2f, "image"))
            add(ListingEntity(4, "title", "description", "location", 2f, "image"))
        }
        daoSut.insert(entries)
        Assert.assertTrue(daoSut.getAll().blockingGet().size == entries.size)
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
        daoSut.removeAll()
        val entries = mutableListOf<ListingEntity>().apply {
            add(ListingEntity(1, "title", "description", "location", 2f, "image"))
            add(ListingEntity(2, "title", "description", "location", 2f, "image"))
            add(ListingEntity(3, "title", "description", "location", 2f, "image"))
            add(ListingEntity(4, "title", "description", "location", 2f, "image"))
        }
        daoSut.insert(entries)

        val originalSize = database.listingEntityDao().getAll().blockingGet().size

        val entry1 = ListingEntity(1, "title1", "description", "location", 2f, "image")
        val entry2 = ListingEntity(4, "title4", "description", "location", 2f, "image")
        val newEntries = listOf(entry1, entry2)
        database.listingEntityDao().insert(newEntries)

        val newEntriesFromTable = database.listingEntityDao().getAll().blockingGet()

        Assert.assertTrue(newEntriesFromTable.size == (originalSize))
        Assert.assertTrue(newEntriesFromTable[0].title == entry1.title)
        Assert.assertTrue(newEntriesFromTable[3].title == entry2.title)

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
        val entries = mutableListOf<ListingEntity>().apply {
            add(ListingEntity(1, "title", "description", "location", 2f, "image"))
            add(ListingEntity(2, "title", "description", "location", 2f, "image"))
            add(ListingEntity(3, "title", "description", "location", 2f, "image"))
            add(ListingEntity(4, "title", "description", "location", 2f, "image"))
        }
        daoSut.insert(entries)
        daoSut.removeAll()
        Assert.assertTrue(daoSut.getAll().blockingGet().isEmpty())
    }

}
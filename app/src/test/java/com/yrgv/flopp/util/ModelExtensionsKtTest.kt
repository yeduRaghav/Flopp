package com.yrgv.flopp.util

import com.yrgv.flopp.data.db.listings.ListingEntity
import org.junit.Assert
import org.junit.Test

/**
 * Tests for model and data transformations
 */
class ModelExtensionsKtTest {

    @Test
    fun testListOfListingApiItemToListingEntitiesHasSameSize() {
        //todo: assert List<ListingApiItem>.toListingEntities(): List<ListingEntity>
        // returns a collection of same size as the original one
    }

    @Test
    fun testListingApiItemToListingEntityValues() {
        //todo: assert ListingApiItem.toListingEntity():ListingEntity  return resulting model with correct values
    }

    @Test
    fun testListingDetailEntityToLocalModel() {
        val listingEntity = ListingEntity(1, "title", "description", "location", 2f, "image")
        val localModel = listingEntity.toListItem()
        val expectedTitle =
            "${listingEntity.id}" + " " + listingEntity.title //not the best way instead, this conversion should be a separate function used in listingEntity.toListItem()
        Assert.assertTrue(localModel.title == expectedTitle)
        Assert.assertTrue(localModel.image == listingEntity.image)
        Assert.assertTrue(localModel.location == listingEntity.location)
    }

    @Test
    fun testToLocalCurrencyFormatResult() {
        Assert.assertTrue(2f.toLocalCurrencyFormat() == "$ 2.0")
    }

    @Test
    fun testLocationToLocalFormatResultWithNonEmptyLine2() {
        //todo: Assert the format of string returned is as expected
    }

    @Test
    fun testLocationToLocalFormatResultWithEmptyLine2() {
        //todo: Assert the format of string returned is as expected fo this case
    }
    @Test
    fun testLocationToLocalFormatResultWithNullLine2() {
        //todo: Assert the format of string returned is as expected fo this case
    }
}
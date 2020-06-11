package com.yrgv.flopp.util

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
    fun testToLocalCurrencyFormatResult() {
        //todo: Assert Float.toLocalCurrencyFormat():String returns value in expected format.
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
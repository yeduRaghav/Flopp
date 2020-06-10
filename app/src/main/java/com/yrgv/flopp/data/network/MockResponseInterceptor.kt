package com.yrgv.flopp.data.network

import android.app.Application
import okhttp3.*

/**
 * This interceptor will hijack the api calls and returns fake local mock data
 */
class MockResponseInterceptor(private val application: Application) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseJson = getMockJsonResponse(uri)
        return chain.proceed(chain.request())
            .newBuilder()
            .code(getResponseCode(responseJson))
            .protocol(Protocol.HTTP_2)
            .message(responseJson)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    responseJson.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()
    }

    private fun getMockJsonResponse(uri: String): String {
        if (isDetailCall(uri)) {
            return getListingDetail()
        }
        val page = getListingPage(uri)
        if (page > 0) {
            return getListingsResponse(page)
        }
        return ""
    }

    private fun getListingPage(uri: String): Int {
        return uri.substringAfter("page=", "0").toInt()
    }

    private fun isDetailCall(uri: String): Boolean {
        return uri.contains("detail")
    }

    private fun getListingsResponse(page: Int): String {
        return if (page == 1) {
            readAssetString("listings_1.json")
        } else {
            readAssetString("listings_2.json")
        }
    }

    private fun getListingDetail(): String {
        return readAssetString("detail.json")
    }

    private fun readAssetString(fileName: String): String {
        return application.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun getResponseCode(response: String): Int {
        return if (response.isEmpty()) {
            400
        } else {
            200
        }
    }
}
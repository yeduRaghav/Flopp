package com.yrgv.flopp.data.network

import android.app.Application
import okhttp3.*

/**
 * This interceptor will hijack the api calls and return local mock data
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
        if (isListingPageCall(uri)) {
            return getListingsResponse()
        }
        if (isDetailCall(uri)) {
            return getListingDetail()
        }
        return ""
    }

    private fun isListingPageCall(uri: String): Boolean {
        return uri.contains("listings/?page")
    }

    private fun isDetailCall(uri: String): Boolean {
        return uri.contains("detail")
    }

    private fun getListingsResponse(): String {
        return readAssetString("listings.json")
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
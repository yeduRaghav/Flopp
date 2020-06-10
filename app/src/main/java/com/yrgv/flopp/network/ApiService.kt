package com.yrgv.flopp.network

import android.app.Application
import com.yrgv.flopp.network.model.Listing
import com.yrgv.flopp.network.model.ListingDetail
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API
 * */
interface ApiService {

    companion object {
        private const val BASE_URL = "https://www.google.com"
        private lateinit var apiInstance: ApiService

        fun getInstance(application: Application): ApiService {
            if (!::apiInstance.isInitialized) {
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(MockResponseInterceptor(application))
                    .build()

                apiInstance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .build()
                    .create(ApiService::class.java)
            }
            return apiInstance
        }
    }

    @GET("/listings/")
    fun getListings(
        @Query("page") page: Int
    ): Call<List<Listing>>

    @GET("/detail")
    fun getDetail(
        @Query("id") id: Long
    ): Call<ListingDetail>

}
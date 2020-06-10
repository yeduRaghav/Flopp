package com.yrgv.flopp.network

import java.net.UnknownHostException

/**
 * Defines localized error responses and their associated data.
 */
sealed class ApiError {
    object BadRequest : ApiError()
    data class GenericError(val errorBody: String?) : ApiError()
    data class NetworkError(val message: String?) : ApiError()

    companion object {
        const val RESPONSE_CODE_BAD_REQUEST = 400

        /**
         * Returns an appropriate localized error object.
         * */
        fun getLocalizedErrorResponse(responseCode: Int, errorBody: String?): ApiError {
            return when (responseCode) {
                RESPONSE_CODE_BAD_REQUEST -> BadRequest
                else -> GenericError(errorBody)
            }
        }

        fun getLocalizedErrorResponse(exception: Throwable? = null): ApiError {
            return when (exception) {
                is UnknownHostException -> NetworkError(exception.message)
                else -> GenericError(exception?.message)
            }
        }
    }
}
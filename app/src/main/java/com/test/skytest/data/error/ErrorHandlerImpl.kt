package com.test.skytest.data.error

import com.test.skytest.domain.error.ErrorEntity
import com.test.skytest.domain.error.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {
    override fun getError(error: Throwable): ErrorEntity {
        return when (error) {
            is IOException -> ErrorEntity.NoConnection
            is HttpException -> {
                when (error.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound
                    else -> ErrorEntity.Unknown
                }
            }
            else -> ErrorEntity.Unknown
        }
    }
}
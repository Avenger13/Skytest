package com.test.skytest.domain.error


interface ErrorHandler {
    fun getError(error:Throwable):ErrorEntity
}
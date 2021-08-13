package com.test.skytest.di.data.network

import okhttp3.Interceptor

class InterceptorsProvider(private val interceptors: List<Interceptor>) {
    fun get() = interceptors
}
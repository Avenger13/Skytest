package com.test.skytest.di.data.network

import com.test.skytest.data.network.api.search.WordsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object ApiModule {
    @Provides
    @Singleton
    fun wordsApi(retrofit: Retrofit): WordsApi {
        return retrofit.create(WordsApi::class.java)
    }
}
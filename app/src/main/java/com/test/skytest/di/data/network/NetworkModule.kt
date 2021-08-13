package com.test.skytest.di.data.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.test.skytest.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
object NetworkModule {

    @Provides
    fun gson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun okHttpClient(
        @ApiInterceptors
        interceptorsProvider: InterceptorsProvider,
        @ApiNetworkInterceptors
        networkInterceptorsProvider: InterceptorsProvider
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            for (i in interceptorsProvider.get())
                addInterceptor(i)
            for (i in networkInterceptorsProvider.get())
                addNetworkInterceptor(i)
        }.build()
    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng.ru/api/public/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @ApiInterceptors
    fun interceptors(): InterceptorsProvider {
        return InterceptorsProvider(emptyList())
    }

    @Provides
    @ApiNetworkInterceptors
    fun networkInterceptors(): InterceptorsProvider {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        return InterceptorsProvider(listOf(loggingInterceptor))
    }


    @Provides
    @Singleton
    fun picasso(context: Context, okHttpClient: OkHttpClient): Picasso {
        return Picasso.Builder(context)
            .downloader(OkHttp3Downloader(okHttpClient))
            .build()
    }

}
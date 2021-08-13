package com.test.skytest.data.network.api.search

import com.test.skytest.data.network.api.search.response.MeaningFull
import com.test.skytest.data.network.api.search.response.Word
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WordsApi {

    @GET("v1/words/search")
    fun search(
        @Query("search") search: String,
        @Query("page") page: Int? = null,
        @Query("pageSize") pageSize: Int? = null
    ): Single<List<Word>>

    @GET("v1/meanings")
    fun meanings(@Query("ids") ids: String): Single<List<MeaningFull>>


}
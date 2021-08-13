package com.test.skytest.data.repository

import com.test.skytest.data.network.api.search.WordsApi
import com.test.skytest.data.network.api.search.response.MeaningFull
import com.test.skytest.data.network.api.search.response.Word
import io.reactivex.Single
import retrofit2.http.Query
import javax.inject.Inject


class WordsRepository @Inject constructor(
    private val wordsApi: WordsApi
) {

    fun search(query: String, page: Int? = null, pageSize: Int? = null): Single<List<Word>> {
        // could be some caching
        return wordsApi.search(query)
    }

    fun meanings(vararg ids: Long): Single<List<MeaningFull>> {
        return wordsApi.meanings(ids.joinToString { it.toString() })
    }
}
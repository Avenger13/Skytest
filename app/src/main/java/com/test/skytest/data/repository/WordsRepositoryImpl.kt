package com.test.skytest.data.repository

import com.test.skytest.data.network.api.search.WordsApi
import com.test.skytest.data.network.api.search.response.MeaningFull
import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.domain.Result
import com.test.skytest.domain.error.ErrorHandler
import com.test.skytest.domain.repository.WordsRepository
import io.reactivex.Single
import javax.inject.Inject


class WordsRepositoryImpl @Inject constructor(
    private val wordsApi: WordsApi,
) : WordsRepository {
    companion object {
        const val MAX_PAGE_SIZE = 15
    }

    override fun search(query: String, page: Int?, pageSize: Int?): Single<List<Word>> {
        // could be some caching
        return wordsApi.search(query, page, pageSize)
    }

    override fun meanings(vararg ids: Long): Single<List<MeaningFull>> {
        return wordsApi.meanings(ids.joinToString { it.toString() })
    }
}
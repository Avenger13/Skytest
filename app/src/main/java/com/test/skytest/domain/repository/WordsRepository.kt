package com.test.skytest.domain.repository

import com.test.skytest.data.network.api.search.response.MeaningFull
import com.test.skytest.data.network.api.search.response.Word
import io.reactivex.Single

interface WordsRepository {
    fun search(query: String, page: Int? = null, pageSize: Int? = null): Single<List<Word>>
    fun meanings(vararg ids: Long): Single<List<MeaningFull>>
}
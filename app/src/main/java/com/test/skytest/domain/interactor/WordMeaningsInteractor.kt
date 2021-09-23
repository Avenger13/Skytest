package com.test.skytest.domain.interactor

import com.test.skytest.common.extension.toResult
import com.test.skytest.data.network.api.search.response.MeaningFull
import com.test.skytest.domain.Result
import com.test.skytest.domain.error.ErrorHandler
import com.test.skytest.domain.repository.WordsRepository
import io.reactivex.Single
import javax.inject.Inject


class WordMeaningsInteractor @Inject constructor(
    private val wordsRepository: WordsRepository,
    private val errorHandler: ErrorHandler
) {
    fun meanings(vararg ids: Long): Single<Result<List<MeaningFull>>> {
        return wordsRepository.meanings(*ids)
            .toResult(errorHandler)
    }
}
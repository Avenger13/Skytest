package com.test.skytest.screen.meaning

import com.test.skytest.data.repository.WordsRepository
import com.test.skytest.presentation.BasePresenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


class MeaningPresenter @AssistedInject constructor(
    @Assisted("meaningIds")
    private val meaningIds: LongArray,
    private val wordsRepository: WordsRepository
) : BasePresenter<MeaningView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        wordsRepository.meanings(*meaningIds)
            .ioToMain()
            .subscribe(
                { viewState.showMeanings(it) },
                { viewState.showError(it.message ?: "") }
            ).autoDispose()

    }
}
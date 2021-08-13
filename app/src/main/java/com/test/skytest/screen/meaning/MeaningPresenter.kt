package com.test.skytest.screen.meaning

import com.test.skytest.data.repository.WordsRepository
import com.test.skytest.presentation.BasePresenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


class MeaningPresenter @AssistedInject constructor(
    @Assisted("meaningId")
    private val meaningId: LongArray,
    private val wordsRepository: WordsRepository
) : BasePresenter<MeaningView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        wordsRepository.meanings(*meaningId)
            .ioToMain()
            .subscribe(
                { viewState.showMeanings(it) },
                { viewState.showError(it.message ?: "") }
            ).autoDispose()

    }
}
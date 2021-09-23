package com.test.skytest.screen.meaning

import com.test.skytest.data.network.api.search.response.MeaningFull
import com.test.skytest.domain.interactor.WordMeaningsInteractor
import com.test.skytest.presentation.BasePresenter
import com.test.skytest.presentation.Resource
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


class MeaningPresenter @AssistedInject constructor(
    @Assisted("meaningIds")
    private val meaningIds: LongArray,
    private val wordMeaningsInteractor:WordMeaningsInteractor
) : BasePresenter<MeaningView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        wordMeaningsInteractor.meanings(*meaningIds)
            .ioToMain()
            .onSuccess (::showMeanings)
            .autoDispose()
    }

    private fun showMeanings(it: List<MeaningFull>) {
        viewState.showMeanings(it)
    }
}
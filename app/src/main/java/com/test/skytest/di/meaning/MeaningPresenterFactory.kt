package com.test.skytest.di.meaning

import com.test.skytest.screen.meaning.MeaningPresenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

@AssistedFactory
interface MeaningPresenterFactory {
    fun create(@Assisted("meaningId") meaningIds: LongArray): MeaningPresenter
}
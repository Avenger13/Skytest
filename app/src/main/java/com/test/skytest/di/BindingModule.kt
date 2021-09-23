package com.test.skytest.di

import com.test.skytest.data.error.ErrorHandlerImpl
import com.test.skytest.data.repository.WordsRepositoryImpl
import com.test.skytest.domain.error.ErrorHandler
import com.test.skytest.domain.repository.WordsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface BindingModule {

    @Binds
    @Singleton
    fun bindErrorHandler(errorHandler: ErrorHandlerImpl): ErrorHandler

    @Binds
    fun bindWordsRepository(wordsRepository: WordsRepositoryImpl): WordsRepository
}
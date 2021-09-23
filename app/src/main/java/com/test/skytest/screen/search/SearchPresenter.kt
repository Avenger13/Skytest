package com.test.skytest.screen.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.test.skytest.data.network.api.search.SearchWordSource
import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.domain.repository.WordsRepository
import com.test.skytest.presentation.BasePresenter
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@ExperimentalCoroutinesApi
class SearchPresenter @Inject constructor(
    private val wordsRepository: WordsRepository
) : BasePresenter<SearchView>() {

    private var query: String? = null
    private val searchSubject = PublishSubject.create<String?>()

    val words: Flow<PagingData<Word>> = Pager(PagingConfig(10,enablePlaceholders = true)) {
        SearchWordSource(wordsRepository, query)
    }.flow

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        searchSubject.debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { viewState.refresh() }
            .autoDispose()
    }


    fun onSearch(query: String?) {
        this.query = query
        searchSubject.onNext(query ?: "")
    }

    fun onWordClick(word: Word) {
        viewState.showMeanings(word.meanings.map { it.id }.toLongArray())
    }

    fun onRetry() {
        viewState.retry()
    }


}
package com.test.skytest.screen.search

import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.data.repository.WordsRepository
import com.test.skytest.presentation.BasePresenter
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchPresenter @Inject constructor(
    private val wordsRepository: WordsRepository
) : BasePresenter<SearchView>() {

    private val searchSubject = PublishSubject.create<String?>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        searchSubject.debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMapSingle { wordsRepository.search(it).wrapByNotification() }
            .ioToMain()
            .doOnSubscribe { viewState.showProgress(true) }
            .doAfterTerminate { viewState.showProgress(false) }
            .subscribe {
                when {
                    it.isOnNext -> {
                        viewState.showSearchResults(it.value!!)
                    }
                    it.isOnError -> {
                        viewState.showError(it.error?.message ?: "")
                    }
                }
            }
            .autoDispose()
    }

    fun onSearch(query: String?) {
        if (query !=null)
            searchSubject.onNext(query)
        else
            viewState.showSearchResults(emptyList())
    }

    fun onWordClick(word: Word) {
        viewState.showMeanings(word.meanings.map { it.id }.toLongArray())
    }

}
package com.test.skytest.screen.search

import androidx.paging.PagingData
import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.presentation.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@OneExecution
interface SearchView : BaseView {

//    @AddToEndSingle
//    fun showSearchResults(words: PagingData<Word>)

    fun showMeanings(ids: LongArray)

    fun refresh()

    fun retry()
}
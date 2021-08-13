package com.test.skytest.screen.search

import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.presentation.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface SearchView : BaseView {
    fun showSearchResults(words: List<Word>)

    @OneExecution
    fun showProgress(show: Boolean)

    @OneExecution
    fun showMeanings(ids: LongArray)
}
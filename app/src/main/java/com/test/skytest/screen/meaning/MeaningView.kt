package com.test.skytest.screen.meaning

import com.test.skytest.data.network.api.search.response.MeaningFull
import com.test.skytest.presentation.BaseView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MeaningView : BaseView {

    fun showMeanings(meanings:List<MeaningFull>)
}
package com.test.skytest.presentation

import moxy.MvpView
import moxy.viewstate.strategy.alias.OneExecution


interface BaseView : MvpView {
    @OneExecution
    fun showError(text: String)
}
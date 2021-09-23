package com.test.skytest.presentation

import com.test.skytest.App
import com.test.skytest.di.AppComponent
import com.test.skytest.ui.extension.toast
import moxy.MvpAppCompatFragment


open class BaseMvpFragment(contentLayoutId: Int) : MvpAppCompatFragment(contentLayoutId), BaseView {
    protected val appComponent: AppComponent
        get() = App.appComponent

    override fun showError(text: String) {
        requireContext().toast(text)
    }
}
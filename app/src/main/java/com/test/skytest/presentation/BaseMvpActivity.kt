package com.test.skytest.presentation

import androidx.annotation.LayoutRes
import com.test.skytest.ui.extension.toast
import moxy.MvpAppCompatActivity


open class BaseMvpActivity : MvpAppCompatActivity, BaseView {
    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    override fun showError(text: String) {
        toast(text)
    }

}
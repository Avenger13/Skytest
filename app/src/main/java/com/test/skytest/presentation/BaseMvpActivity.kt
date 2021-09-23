package com.test.skytest.presentation

import androidx.annotation.LayoutRes
import by.kirich1409.viewbindingdelegate.internal.findRootView
import com.test.skytest.ui.extension.snackBar
import com.test.skytest.ui.extension.toast
import moxy.MvpAppCompatActivity


open class BaseMvpActivity : MvpAppCompatActivity, BaseView {
    constructor() : super()
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    override fun showError(text: String) {
        snackBar(findRootView(this), text)
    }

}
package com.test.skytest.presentation

import com.test.skytest.ui.extension.toast
import moxy.MvpAppCompatActivity


open class BaseMvpActivity : MvpAppCompatActivity(), BaseView {

    override fun showError(text: String) {
        toast(text)
    }

}
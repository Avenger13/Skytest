package com.test.skytest.presentation

import android.widget.Toast
import moxy.MvpAppCompatActivity


open class BaseMvpActivity: MvpAppCompatActivity(), BaseView {

    override fun showError(text: String) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }

}
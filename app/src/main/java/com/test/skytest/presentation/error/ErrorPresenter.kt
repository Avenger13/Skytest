package com.test.skytest.presentation.error

import androidx.annotation.CallSuper
import com.test.skytest.domain.error.ErrorEntity

abstract class ErrorPresenter(private val delegate: ErrorPresenter? = null) {

    @CallSuper
    open fun resolve(error: ErrorEntity) {
        //just calling the delegate
        delegate?.resolve(error)
    }
}


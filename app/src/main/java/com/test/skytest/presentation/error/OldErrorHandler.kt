package com.test.skytest.presentation.error

abstract class OldErrorHandler(private val delegate: OldErrorHandler? = null ) {

    fun handle(error: Throwable) {
        //just calling the delegate
        delegate?.onError(error)
    }

    abstract fun onError(error: Throwable)
}


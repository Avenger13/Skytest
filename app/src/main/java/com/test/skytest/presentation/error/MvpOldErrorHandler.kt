package com.test.skytest.presentation.error

import android.util.Log
import com.test.skytest.presentation.BaseView
import retrofit2.HttpException

class MvpOldErrorHandler<V : BaseView>(
    private val viewState: V,
    delegate: OldErrorHandler? = null
) : OldErrorHandler(delegate) {

    override fun onError(error: Throwable) {
        Log.e("ErrorHandler", " - [MvpErrorHandler], error:", error)

        when (error) {
            is HttpException -> {
                viewState.showError(error.message())
            }
            else -> {
                viewState.showError(error.message ?: "NO ERR MSG")
            }
        }
    }
}
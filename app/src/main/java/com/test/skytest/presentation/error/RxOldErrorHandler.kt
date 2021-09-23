package com.test.skytest.presentation.error

import android.util.Log
import io.reactivex.functions.Consumer

class RxOldErrorHandler(delegate: OldErrorHandler? = null) : OldErrorHandler(delegate),
    Consumer<Throwable> {

    override fun accept(error: Throwable) {
        onError(error)
        handle(error)
    }

    override fun onError(error: Throwable) {
        //just logging, no actions visible to user
        Log.e("ErrorHandler", " - [RxErrorHandler], error:", error)
    }
}
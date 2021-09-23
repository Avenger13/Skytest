package com.test.skytest.common.extension

import com.test.skytest.domain.Result
import com.test.skytest.domain.error.ErrorHandler
import io.reactivex.Single


fun <T> Single<T>.toResult(errorHandler: ErrorHandler) =
    map<Result<T>> { Result.Success(it) }
        .onErrorReturn { Result.Error(errorHandler.getError(it)) }
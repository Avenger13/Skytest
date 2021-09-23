package com.test.skytest.domain.error


sealed class ErrorEntity {
    object NoConnection : ErrorEntity()
    object NotFound : ErrorEntity()
    object Unknown : ErrorEntity()
}

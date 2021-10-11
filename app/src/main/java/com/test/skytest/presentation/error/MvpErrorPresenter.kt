package com.test.skytest.presentation.error

import com.test.skytest.R
import com.test.skytest.domain.error.ErrorEntity
import com.test.skytest.presentation.BaseView
import com.test.skytest.presentation.Resource

class MvpErrorPresenter<V : BaseView>(
    private val viewState: V,
    private val resource: Resource<Int>,
    delegate: ErrorPresenter? = null
) : ErrorPresenter(delegate) {


    override fun resolve(error: ErrorEntity) {

        when (error) {
            ErrorEntity.NoConnection -> {
                viewState.showError(resource.getString(R.string.error_no_connection))
            }
            // TODO (add different error msgs)
            ErrorEntity.NotFound -> {
                viewState.showError(resource.getString(R.string.error_no_connection))
            }
            ErrorEntity.Unknown -> {
                viewState.showError(resource.getString(R.string.error_no_connection))
            }
        }

        super.resolve(error)
    }
}
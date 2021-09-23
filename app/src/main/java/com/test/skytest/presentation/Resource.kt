package com.test.skytest.presentation

import android.graphics.drawable.Drawable
import org.jetbrains.annotations.NotNull


interface Resource<Identifier:Any> {

    @NotNull
    fun getString(identifier: Identifier): String

    @NotNull
    fun getString(identifier: Identifier, formatArgs: Any): String

    @NotNull
    fun getColor(identifier: Identifier): Int

    fun getDrawable(identifier: Identifier): Drawable?

}
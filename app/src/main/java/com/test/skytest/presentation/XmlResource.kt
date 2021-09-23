package com.test.skytest.presentation

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import javax.inject.Inject

class XmlResource @Inject constructor(
    private val resources: Resources,
    private val theme: Resources.Theme? = null
) : Resource<Int> {

    override fun getString(identifier: Int): String {
        return resources.getString(identifier)
    }

    override fun getString(identifier: Int, formatArgs: Any): String {
        return resources.getString(identifier, formatArgs)
    }

    override fun getColor(identifier: Int): Int {
        return ResourcesCompat.getColor(resources, identifier, theme)
    }

    override fun getDrawable(identifier: Int): Drawable? {
        return ResourcesCompat.getDrawable(resources, identifier, theme)
    }
}
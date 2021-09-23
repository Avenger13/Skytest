package com.test.skytest.di

import android.content.Context
import com.test.skytest.presentation.Resource
import com.test.skytest.presentation.XmlResource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [BindingModule::class])
object AppModule {
    @Provides
    @Singleton
    fun provideResource(context: Context): Resource<Int> =
        XmlResource(context.resources, context.theme)

}
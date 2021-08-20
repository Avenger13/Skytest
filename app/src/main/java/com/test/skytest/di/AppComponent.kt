package com.test.skytest.di

import android.content.Context
import com.squareup.picasso.Picasso
import com.test.skytest.di.data.network.NetworkModule
import com.test.skytest.screen.meaning.MeaningActivity
import com.test.skytest.screen.search.SearchActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(searchActivity: SearchActivity)
    fun inject(meaningActivity: MeaningActivity)

    val picasso: Picasso

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
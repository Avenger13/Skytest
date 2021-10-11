package com.test.skytest.di

import android.content.Context
import com.squareup.picasso.Picasso
import com.test.skytest.di.data.network.NetworkModule
import com.test.skytest.presentation.Resource
import com.test.skytest.screen.meaning.MeaningFragment
import com.test.skytest.screen.search.SearchFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, AppModule::class])
@Singleton
interface AppComponent {
    fun inject(searchFragment: SearchFragment)
    fun inject(meaningFragment: MeaningFragment)

    val resource: Resource<Int>
    val picasso: Picasso

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
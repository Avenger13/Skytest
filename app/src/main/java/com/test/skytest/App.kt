package com.test.skytest

import android.app.Application
import com.test.skytest.di.AppComponent
import com.test.skytest.di.DaggerAppComponent


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
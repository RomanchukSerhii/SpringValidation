package com.example.springvalidation

import android.app.Application
import com.example.springvalidation.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FirstMomentApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FirstMomentApp)
            modules(appModule)
        }
    }
}
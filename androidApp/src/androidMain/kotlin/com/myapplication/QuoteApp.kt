package com.myapplication

import android.app.Application
import com.myapplication.di.viewModelModule
import di.getSharedModules
import org.koin.core.context.startKoin

class QuoteApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(viewModelModule + getSharedModules())
        }
    }
}
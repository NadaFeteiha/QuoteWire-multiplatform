package com.myapplication

import android.app.Application
import com.myapplication.di.viewModelModule
import di.initKoin
import org.koin.android.ext.koin.androidContext

class QuoteApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@QuoteApp)
            modules(viewModelModule)
        }
    }
}
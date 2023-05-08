package com.myapplication

import android.app.Application
import com.myapplication.di.viewModelModule
import di.getSharedModules
import di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class QuoteApp : Application() {

    override fun onCreate() {
        super.onCreate()
//        startKoin {
//            modules(viewModelModule + getSharedModules())
//            androidContext(this@QuoteApp)
//        }
        initKoin {
            androidContext(this@QuoteApp)
            modules(
                viewModelModule
            )
        }
    }
}
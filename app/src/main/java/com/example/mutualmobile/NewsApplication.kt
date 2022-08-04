package com.example.mutualmobile

import android.app.Application
import com.example.mutualmobile.di.appModule
import com.example.mutualmobile.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsApplication)
            modules(listOf(networkModule, appModule))
        }
    }
}
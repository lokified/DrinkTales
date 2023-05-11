package com.loki.cocktail

import android.app.Application
import com.loki.data.di.dataModule
import com.loki.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CockTailApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CockTailApp)
            modules(dataModule, presentationModule)
        }
    }
}
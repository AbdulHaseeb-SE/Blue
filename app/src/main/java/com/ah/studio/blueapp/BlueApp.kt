package com.ah.studio.blueapp

import android.app.Application
import com.ah.studio.blueapp.ui.screens.authentication.di.authenticationModule
import com.ah.studio.blueapp.ui.screens.main.di.bottomNavigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BlueApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@BlueApp)
            modules(
                bottomNavigationModule,
                authenticationModule
            )
        }
    }
}
package com.ah.studio.blueapp.ui.screens.authentication.di

import com.ah.studio.blueapp.SessionManager
import com.ah.studio.blueapp.ui.screens.authentication.AuthenticationViewModel
import com.ah.studio.blueapp.ui.screens.authentication.domain.repository.IUserRepository
import com.ah.studio.blueapp.ui.screens.authentication.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val authenticationModule: Module = module {
    factory<IUserRepository> {
        UserRepository(androidContext())
    }

    factory {
        AuthenticationViewModel(
            get(),
            sessionManager = SessionManager(androidContext())
        )
    }
}
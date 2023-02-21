package com.ah.studio.blueapp.ui.screens.account.di

import com.ah.studio.blueapp.ui.screens.account.AccountViewModel
import com.ah.studio.blueapp.ui.screens.account.domain.repository.AccountRepository
import com.ah.studio.blueapp.ui.screens.account.domain.repository.IAccountRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val accountModule: Module = module {
    factory<IAccountRepository> {
        AccountRepository(androidContext())
    }

    factory {
        AccountViewModel(get())
    }
}
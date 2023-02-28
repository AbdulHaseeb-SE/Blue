package com.ah.studio.blueapp.ui.screens.home.di

import com.ah.studio.blueapp.ui.screens.home.HomeViewModel
import com.ah.studio.blueapp.ui.screens.home.domain.repository.HomeRepository
import com.ah.studio.blueapp.ui.screens.home.domain.repository.IHomeRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val homeModule: Module = module {

    factory<IHomeRepository> {
        HomeRepository(androidContext())
    }

    factory {
        HomeViewModel(get())
    }
}
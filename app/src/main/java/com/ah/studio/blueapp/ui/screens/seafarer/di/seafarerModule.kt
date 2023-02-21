package com.ah.studio.blueapp.ui.screens.seafarer.di

import com.ah.studio.blueapp.ui.screens.seafarer.SeafarerViewModel
import com.ah.studio.blueapp.ui.screens.seafarer.domain.repository.ISeafarerRepository
import com.ah.studio.blueapp.ui.screens.seafarer.domain.repository.SeafarerRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val seafarerModule: Module = module {

    factory<ISeafarerRepository> {
        SeafarerRepository(androidContext())
    }

    factory {
        SeafarerViewModel(get())
    }

}
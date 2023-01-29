package com.ah.studio.blueapp.ui.screens.main.di

import com.ah.studio.blueapp.ui.screens.main.domain.repository.BottomNavItemRepository
import com.ah.studio.blueapp.ui.screens.main.domain.repository.IBottomNavItemRepository
import com.ah.studio.blueapp.ui.screens.main.viewModel.BottomNavViewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val bottomNavigationModule: Module = module {
    factory<IBottomNavItemRepository> {
        BottomNavItemRepository()
    }

    factory {
        BottomNavViewModel(
            get()
        )
    }
}
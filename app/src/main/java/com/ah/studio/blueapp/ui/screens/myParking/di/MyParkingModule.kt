package com.ah.studio.blueapp.ui.screens.myParking.di

import com.ah.studio.blueapp.ui.screens.myParking.ParkingViewModel
import com.ah.studio.blueapp.ui.screens.myParking.domain.repository.IParkingRepository
import com.ah.studio.blueapp.ui.screens.myParking.domain.repository.ParkingRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val myParkingModule: Module = module {
    factory<IParkingRepository> {
        ParkingRepository(androidContext())
    }

    factory {
        ParkingViewModel(get())
    }
}
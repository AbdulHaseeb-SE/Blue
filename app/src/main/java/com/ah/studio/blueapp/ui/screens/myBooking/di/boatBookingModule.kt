package com.ah.studio.blueapp.ui.screens.myBooking.di

import com.ah.studio.blueapp.ui.screens.myBooking.BoatBookingViewModel
import com.ah.studio.blueapp.ui.screens.myBooking.domain.repository.IMyBookingRepository
import com.ah.studio.blueapp.ui.screens.myBooking.domain.repository.MyBookingRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module


val boatBookingModule: Module = module {
    factory<IMyBookingRepository> {
        MyBookingRepository(androidContext())
    }
    factory {
        BoatBookingViewModel(get())
    }
}
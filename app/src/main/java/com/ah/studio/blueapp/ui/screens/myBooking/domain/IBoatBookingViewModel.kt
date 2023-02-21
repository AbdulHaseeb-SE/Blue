package com.ah.studio.blueapp.ui.screens.myBooking.domain

import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.BoatBookingListResponse
import kotlinx.coroutines.flow.Flow

interface IBoatBookingViewModel {
    fun getBoatBookingListResponse()
    val boatBookingListResponse: Flow<BoatBookingListResponse?>
}
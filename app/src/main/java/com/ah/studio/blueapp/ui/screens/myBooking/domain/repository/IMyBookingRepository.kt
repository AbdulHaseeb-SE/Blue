package com.ah.studio.blueapp.ui.screens.myBooking.domain.repository

import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.BoatBookingListResponse
import kotlinx.coroutines.flow.MutableStateFlow

interface IMyBookingRepository {
    fun getBoatBookingList(
        bookingListResponse: (BoatBookingListResponse)-> Unit
    ): MutableStateFlow<String?>
}
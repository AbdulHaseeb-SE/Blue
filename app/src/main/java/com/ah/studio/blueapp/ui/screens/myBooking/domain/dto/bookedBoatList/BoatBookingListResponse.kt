package com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatList

data class BoatBookingListResponse(
    val `data`: List<BookingList>,
    val message: String,
    val status: Int,
    val type: String
)
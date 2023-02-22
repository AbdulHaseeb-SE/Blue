package com.ah.studio.blueapp.ui.screens.myParking.domain.dto.myParkingBooking

data class MyParkingBookingResponse(
    val `data`: List<ParkedBoat>,
    val last_page: Int,
    val message: String,
    val page: Int,
    val status: Int,
    val total: Int,
    val type: String
)
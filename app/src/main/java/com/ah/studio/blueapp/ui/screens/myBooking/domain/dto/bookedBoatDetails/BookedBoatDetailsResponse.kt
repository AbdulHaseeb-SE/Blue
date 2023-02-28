package com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatDetails

data class BookedBoatDetailsResponse(
    val `data`: BookedBoatDetail?,
    val message: String,
    val status: Int,
    val type: String
)
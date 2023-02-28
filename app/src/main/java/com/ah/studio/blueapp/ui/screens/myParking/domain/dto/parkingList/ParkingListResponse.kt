package com.ah.studio.blueapp.ui.screens.myParking.domain.dto.parkingList

data class ParkingListResponse(
    val `data`: List<Boat>,
    val last_page: Int,
    val message: String,
    val page: Int,
    val status: Int,
    val total: Int,
    val type: String
)
package com.ah.studio.blueapp.ui.screens.myParking.domain.dto.parkingList

data class Boat(
    val address: String,
    val from_date: String,
    val id: Int,
    val image: String,
    val lat: String,
    val long: String,
    val name: String,
    val parking_status: String,
    val price: String,
    val price_type: String,
    val size: Boolean,
    val to_date: String
)
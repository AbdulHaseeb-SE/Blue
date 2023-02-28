package com.ah.studio.blueapp.ui.screens.home.domain.dto.boats

data class BoatResponse(
    val `data`: List<Boat>,
    val last_page: Int,
    val message: String,
    val page: Int,
    val status: Int,
    val total: Int,
    val type: String
)
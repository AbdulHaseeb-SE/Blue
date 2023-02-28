package com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails

data class BoatDetailsResponse(
    val `data`: List<BoatDetails>,
    val message: String,
    val status: Int,
    val type: String
)
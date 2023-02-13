package com.ah.studio.blueapp.ui.screens.home.domain.dto

import androidx.compose.ui.graphics.painter.Painter

data class BoatDetailsParking(
    val boatImage: Painter,
    val  boatName: String,
    val location: String,
    val price: String,
    val parkingStatus: Boolean = false
)

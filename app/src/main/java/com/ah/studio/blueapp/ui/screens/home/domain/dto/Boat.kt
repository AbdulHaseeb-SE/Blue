package com.ah.studio.blueapp.ui.screens.home.domain.dto

import androidx.compose.ui.graphics.painter.Painter

data class Boat(
    val boatImage: Painter,
    val  boatName: String,
    val location: String,
    val price: String
)

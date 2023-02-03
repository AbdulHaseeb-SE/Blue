package com.ah.studio.blueapp.ui.screens.home.domain.dto

import androidx.compose.ui.graphics.painter.Painter

data class RestaurantMenuItem(
    val itemName: String,
    val itemDescription: String,
    val price: String,
    val timeRequired: String = "30 Mins",
    val image: Painter
)
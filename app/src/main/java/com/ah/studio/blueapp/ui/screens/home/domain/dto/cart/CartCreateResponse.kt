package com.ah.studio.blueapp.ui.screens.home.domain.dto.cart

data class CartCreateResponse(
    val `data`: List<Any>,
    val message: String,
    val status: Int,
    val type: String
)
package com.ah.studio.blueapp.ui.screens.home.domain.dto.cart

data class CartListResponse(
    val `data`: List<CartItems>,
    val message: String,
    val status: Int,
    val type: String
)
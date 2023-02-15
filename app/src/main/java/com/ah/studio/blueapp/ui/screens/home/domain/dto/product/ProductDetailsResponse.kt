package com.ah.studio.blueapp.ui.screens.home.domain.dto.product

data class ProductDetailsResponse(
    val `data`: ProductDetails,
    val message: String,
    val status: Int,
    val type: String
)
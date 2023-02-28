package com.ah.studio.blueapp.ui.screens.home.domain.dto.cart

data class CreateCartBody(
    val id: Int,
    val product_id: Int,
    val qty: Int,
    val total: Int
)
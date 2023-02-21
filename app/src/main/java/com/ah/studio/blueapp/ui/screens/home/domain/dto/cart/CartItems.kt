package com.ah.studio.blueapp.ui.screens.home.domain.dto.cart

data class CartItems(
    val created_at: String,
    val id: Int,
    val image: String,
    val name: String,
    val product_id: Int,
    val product_price: String,
    val qty: Int,
    val total: String,
    val type: String
)
package com.ah.studio.blueapp.ui.screens.home.domain.dto.product

data class ProductListResponse(
    val `data`: List<Product>,
    val last_page: Int,
    val message: String,
    val page: Int,
    val status: Int,
    val total: Int,
    val type: String
)
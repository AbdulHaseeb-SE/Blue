package com.ah.studio.blueapp.ui.screens.home.domain.dto.product

data class ProductCategoryResponse(
    val `data`: List<ProductCategory>,
    val message: String,
    val status: Int,
    val type: String
)
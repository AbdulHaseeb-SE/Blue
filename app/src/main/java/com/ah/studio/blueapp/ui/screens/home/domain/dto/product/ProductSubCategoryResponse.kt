package com.ah.studio.blueapp.ui.screens.home.domain.dto.product

data class ProductSubCategoryResponse(
    val `data`: List<ProductSubCategory>,
    val message: String,
    val status: Int,
    val type: String
)
package com.ah.studio.blueapp.ui.screens.home.domain.dto.product

data class ProductDetails(
    val category_name: String,
    val description: String,
    val id: Int,
    val image: String,
    val name: String,
    val price: String,
    val stock: Int,
    val sub_category_name: String,
    val type: String
)
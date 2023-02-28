package com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerCategory

data class SeafarerCategoryResponse(
    val `data`: List<SeafarerCategory>,
    val message: String,
    val status: Int,
    val type: String
)
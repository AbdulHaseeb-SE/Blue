package com.ah.studio.blueapp.ui.screens.home.domain.dto.gallery

data class GalleryImageResponse(
    val `data`: List<Gallery>,
    val message: String,
    val status: Int,
    val type: String
)
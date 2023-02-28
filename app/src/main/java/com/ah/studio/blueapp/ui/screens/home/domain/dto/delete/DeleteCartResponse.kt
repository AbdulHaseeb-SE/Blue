package com.ah.studio.blueapp.ui.screens.home.domain.dto.delete

data class DeleteCartResponse(
    val `data`: List<Any>,
    val message: String,
    val status: Int,
    val type: String
)
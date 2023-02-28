package com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerList

data class SeafarerListResponse(
    val `data`: List<Seafarer>,
    val last_page: Int,
    val message: String,
    val page: Int,
    val status: Int,
    val total: Int,
    val type: String
)
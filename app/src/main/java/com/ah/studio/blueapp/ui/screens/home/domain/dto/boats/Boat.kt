package com.ah.studio.blueapp.ui.screens.home.domain.dto.boats

data class Boat(
    val created_at: String,
    val ending_to: String,
    val featured_image: String,
    val id: Int,
    val name: String,
    val pickup_address: String,
    val starting_from: String
)
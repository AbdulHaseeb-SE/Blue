package com.ah.studio.blueapp.ui.screens.account.domain.dto.userDetails

data class UserDetailsResponse(
    val `data`: UserDetails,
    val message: String,
    val status: Int,
    val type: String
)
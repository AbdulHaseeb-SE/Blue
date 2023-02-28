package com.ah.studio.blueapp.ui.screens.account.domain.dto.userDetails

data class UserDetails(
    val civil_id: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val isFreez: Boolean,
    val isVerified: Boolean,
    val last_name: String,
    val notification_status: Boolean,
    val phone_no: String,
    val role: String
)
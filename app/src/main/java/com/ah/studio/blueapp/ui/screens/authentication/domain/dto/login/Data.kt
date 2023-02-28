package com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login

data class Data(
    val civil_id: String,
    val email: String,
    val first_name: String,
    val isFreez: Int,
    val last_name: String,
    val notification_status: Int,
    val phone_no: String,
    val role: String,
    val token: String
)
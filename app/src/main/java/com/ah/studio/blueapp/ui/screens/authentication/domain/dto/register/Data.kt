package com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register

data class Data(
    val civil_id: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val phone_no: String,
    val role: String,
    val token: String
)
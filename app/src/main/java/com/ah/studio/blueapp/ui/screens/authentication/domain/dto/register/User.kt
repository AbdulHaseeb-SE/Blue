package com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register

data class User(
    val civil_id: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val password: String,
    val phone_no: String,
    val fcm_token: String
)
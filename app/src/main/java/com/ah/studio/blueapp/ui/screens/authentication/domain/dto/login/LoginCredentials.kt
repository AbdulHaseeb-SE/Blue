package com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login

data class LoginCredentials(
    val email: String,
    val fcm_token: String,
    val password: String
)
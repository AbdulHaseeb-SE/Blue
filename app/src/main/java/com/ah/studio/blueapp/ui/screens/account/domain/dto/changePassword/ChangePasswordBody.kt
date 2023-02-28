package com.ah.studio.blueapp.ui.screens.account.domain.dto.changePassword

data class ChangePasswordBody(
    val confirm_password: String,
    val new_password: String,
    val old_password: String
)
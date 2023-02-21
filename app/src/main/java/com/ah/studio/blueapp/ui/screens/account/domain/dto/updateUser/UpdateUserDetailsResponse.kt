package com.ah.studio.blueapp.ui.screens.account.domain.dto.updateUser

data class UpdateUserDetailsResponse(
    val data: UserProfileDetails?,
    val message: String,
    val success: Boolean
)
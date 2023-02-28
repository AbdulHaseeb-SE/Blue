package com.ah.studio.blueapp.ui.screens.account.domain.dto.termsAndConditions

data class TermsAndConditionsResponse(
    val `data`: TermAndCondition,
    val message: String,
    val status: Int,
    val type: String
)
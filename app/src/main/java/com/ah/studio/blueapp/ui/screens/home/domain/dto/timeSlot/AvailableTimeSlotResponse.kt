package com.ah.studio.blueapp.ui.screens.home.domain.dto.timeSlot

data class AvailableTimeSlotResponse(
    val `data`: Map<String, List<Time>>,
    val message: String,
    val status: Int,
    val type: String
)
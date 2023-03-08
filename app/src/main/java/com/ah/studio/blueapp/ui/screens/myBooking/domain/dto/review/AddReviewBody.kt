package com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.review

data class AddReviewBody(
    val boat_product_booking_id: String,
    val description: String,
    val entity_id: String,
    val entity_type: String,
    val rating: String
)
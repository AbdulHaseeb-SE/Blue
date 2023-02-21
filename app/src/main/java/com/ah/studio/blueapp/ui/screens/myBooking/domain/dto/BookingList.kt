package com.ah.studio.blueapp.ui.screens.myBooking.domain.dto

data class BookingList(
    val booking_id_string: String,
    val created_at: String,
    val grand_total: String,
    val id: Int,
    val image: String,
    val name: String,
    val order_ref: Any,
    val payment_method: Any,
    val result_code: String,
    val status: String
)
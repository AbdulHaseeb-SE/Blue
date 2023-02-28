package com.ah.studio.blueapp.ui.screens.home.domain.dto.booking

data class BoatBookingBody(
    val boat_category: String,
    val boat_grand_total: String,
    val boat_id: Int,
    val boat_total: String,
    val coupon_amount: String,
    val coupon_id: String,
    val destination_id: String,
    val end: String,
    val grand_total: String,
    val order_ref: String,
    val package_id: String,
    val product: List<Product>?,
    val start: String,
    val total: String
)
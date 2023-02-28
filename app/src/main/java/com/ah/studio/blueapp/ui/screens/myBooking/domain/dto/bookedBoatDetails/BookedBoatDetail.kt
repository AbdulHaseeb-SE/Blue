package com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatDetails

data class BookedBoatDetail(
    val coupon_amount: Int?,
    val coupon_id: Int,
    val created_at: String,
    val end: String,
    val grand_total: String,
    val grand_total_boat: String,
    val id: Int,
    val image: String,
    val name: String,
    val order_ref: Any,
    val package_name: String,
    val package_price: String,
    val payment_method: Any,
    val pickup_address: String,
    val product: List<Product>,
    val result_code: String,
    val start: String,
    val status: String,
    val total: String
)
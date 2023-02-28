package com.ah.studio.blueapp.ui.screens.myParking.domain.dto.myParkingBooking

data class ParkedBoat(
    val address: String,
    val boat_id: Int,
    val boat_image: Boolean,
    val boat_name: String,
    val boat_type: Any,
    val booking_id_string: Int,
    val coupon_id: Int,
    val created_at: String,
    val from_date: String,
    val grand_total: String,
    val id: Int,
    val image: String,
    val lat: String,
    val long: String,
    val parking_name: String,
    val payment_method: String,
    val payment_status: String,
    val price: String,
    val to_date: String,
    val transaction_id: String
)
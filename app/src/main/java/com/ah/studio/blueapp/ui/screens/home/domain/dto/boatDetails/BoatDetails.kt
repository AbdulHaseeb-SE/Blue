package com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails

data class BoatDetails(
    val boat_rating: Any,
    val boat_review_list: List<Any>,
    val category: Int,
    val created_at: String,
    val description: String,
    val destination_address: List<DestinationAddress>,
    val ending_to: String,
    val facilities: List<Facilities>,
    val featured_image: String,
    val from_date: String,
    val id: Int,
    val latitude: Any,
    val longitude: Any,
    val member_allowed: Int,
    val name: String,
    val packages: List<Package>,
    val pickup_address: String,
    val seafarer_name: List<Any>,
    val starting_from: String,
    val sub_category: Int,
    val to_date: String
)
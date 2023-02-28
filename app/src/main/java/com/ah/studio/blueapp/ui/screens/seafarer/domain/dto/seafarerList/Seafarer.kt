package com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerList

data class Seafarer(
    val age: Int,
    val amount: String,
    val baot_experince: List<String>,
    val category: String,
    val created_at: String,
    val experince: String,
    val id: Int,
    val images: List<String>,
    val language: List<String>,
    val license_number: String,
    val name: String,
    val nationality: String,
    val phone_no: Int,
    val unlock: Boolean
)
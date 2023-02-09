package com.ah.studio.blueapp.ui.screens.home

import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategoryResponse
import kotlinx.coroutines.flow.MutableStateFlow

interface IHomeViewModel {
    val boatCategoryResponse: MutableStateFlow<BoatCategoryResponse?>
    fun getBoatCategoryResponse()
}
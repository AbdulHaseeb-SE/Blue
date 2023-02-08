package com.ah.studio.blueapp.ui.screens.home.domain.repository

import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategoryResponse
import kotlinx.coroutines.flow.StateFlow

interface IHomeRepository {
    fun getBoatCategory() : StateFlow<BoatCategoryResponse>
}
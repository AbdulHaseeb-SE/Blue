package com.ah.studio.blueapp.ui.screens.home.domain.repository

import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategorySubCategoryResponse
import kotlinx.coroutines.flow.MutableStateFlow

interface IHomeRepository {
    suspend fun getBoatCategorySubCategory(
        boatCategorySubCategoryResponse: (BoatCategorySubCategoryResponse) -> Unit
    ): MutableStateFlow<String?>
}
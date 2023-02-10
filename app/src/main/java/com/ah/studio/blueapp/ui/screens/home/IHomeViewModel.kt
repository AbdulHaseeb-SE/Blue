package com.ah.studio.blueapp.ui.screens.home

import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategorySubCategoryResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface IHomeViewModel {
    val boatCategorySubCategoryResponse: Flow<BoatCategorySubCategoryResponse?>
    fun getBoatCategoryResponse(): Flow<String?>

    val categoryList: MutableStateFlow<List<Category>?>

}
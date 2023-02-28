package com.ah.studio.blueapp.ui.screens.seafarer.domain.repository

import com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerCategory.SeafarerCategoryResponse
import com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerList.SeafarerListResponse
import kotlinx.coroutines.flow.MutableStateFlow

interface ISeafarerRepository {
    fun getSeafarerListResponse(
        page: Int,
        category: String,
        seafarerListResponse: (SeafarerListResponse) -> Unit
    ): MutableStateFlow<String?>

    fun getSeafarerCategoryResponse(
        seafarerCategoryResponse: (SeafarerCategoryResponse) -> Unit
    ): MutableStateFlow<String?>
}
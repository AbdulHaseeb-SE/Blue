package com.ah.studio.blueapp.ui.screens.seafarer

import com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerCategory.SeafarerCategoryResponse
import com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerList.SeafarerListResponse
import kotlinx.coroutines.flow.Flow

interface ISeafarerViewModel {

    fun getSeafarerListResponse(
        page: Int,
        category: String
    ): Flow<String?>

    fun getSeafarerCategoryResponse()


    val seafarerListResponse: Flow<SeafarerListResponse?>
    val seafarerCategoryResponse: Flow<SeafarerCategoryResponse?>
}
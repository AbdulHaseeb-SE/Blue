package com.ah.studio.blueapp.ui.screens.home.domain.repository

import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategorySubCategoryResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails.BoatDetailsResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boats.BoatBody
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boats.BoatResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.gallery.GalleryImageResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.timeSlot.AvailableTimeSlotResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.timeSlot.TimeSlotBody
import kotlinx.coroutines.flow.MutableStateFlow

interface IHomeRepository {
    suspend fun getBoatCategorySubCategory(
        boatCategorySubCategoryResponse: (BoatCategorySubCategoryResponse) -> Unit
    ): MutableStateFlow<String?>

    suspend fun getBoatList(
        boatBody: BoatBody,
        boatList: (BoatResponse) -> Unit
    ): MutableStateFlow<String?>

    suspend fun getBoatDetailsResponse(
        boatId: Int,
        boatDetailsResponse: (BoatDetailsResponse) -> Unit
    ): MutableStateFlow<String?>

    suspend fun getGalleryImagesResponse(
        boatId: Int,
        galleryImageResponse: (GalleryImageResponse) -> Unit
    ): MutableStateFlow<String?>
    suspend fun getAvailableTimeSlotResponse(
        timeSlotBody: TimeSlotBody,
        availableTimeSlotResponse: (AvailableTimeSlotResponse) -> Unit
    ): MutableStateFlow<String?>
}
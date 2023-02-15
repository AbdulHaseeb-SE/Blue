package com.ah.studio.blueapp.ui.screens.home

import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategorySubCategoryResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.Category
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.SubCategory
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails.BoatDetailsResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boats.BoatBody
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boats.BoatResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.gallery.GalleryImageResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.product.ProductCategoryResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.product.ProductDetailsResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.product.ProductListResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.product.ProductSubCategoryResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.timeSlot.AvailableTimeSlotResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.timeSlot.TimeSlotBody
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface IHomeViewModel {
    val boatCategorySubCategoryResponse: Flow<BoatCategorySubCategoryResponse?>
    fun getBoatCategoryResponse(): Flow<String?>
    fun getBoatResponse(boatBody: BoatBody): Flow<String?>
    fun getBoatDetailsResponse(boatId: Int): Flow<String?>
    fun getGalleryImageResponse(boatId: Int): Flow<String?>
    fun getAvailableTimeSlotResponse(timeSlotBody: TimeSlotBody): Flow<String?>
    fun getProductCategoryResponse(type: String): Flow<String?>
    fun getProductSubCategoryResponse(productCategoryId: String): Flow<String?>
    fun getProductListResponse(page: Int, subCategoryId: String): Flow<String?>
    fun getProductDetailsResponse(productId: Int): Flow<String?>


    val categoryList: MutableStateFlow<List<Category>?>
    val subCategoryList: MutableStateFlow<List<SubCategory>?>
    val boatResponse: MutableStateFlow<BoatResponse?>
    val boatDetailsResponse: MutableStateFlow<BoatDetailsResponse?>
    val galleryImagesResponse: MutableStateFlow<GalleryImageResponse?>
    val availableTimeSlotResponse: MutableStateFlow<AvailableTimeSlotResponse?>
    val productCategoryResponse: MutableStateFlow<ProductCategoryResponse?>
    val productSubCategoryResponse: MutableStateFlow<ProductSubCategoryResponse?>
    val productListResponse: MutableStateFlow<ProductListResponse?>
    val productDetailsResponse: MutableStateFlow<ProductDetailsResponse?>
}
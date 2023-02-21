package com.ah.studio.blueapp.ui.screens.home.domain.repository

import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategorySubCategoryResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails.BoatDetailsResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boats.BoatBody
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boats.BoatResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.booking.BoatBookingBody
import com.ah.studio.blueapp.ui.screens.home.domain.dto.booking.BoatBookingResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.cart.CartCreateResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.cart.CartListResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.cart.CreateCartBody
import com.ah.studio.blueapp.ui.screens.home.domain.dto.delete.DeleteCartResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.gallery.GalleryImageResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.product.ProductCategoryResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.product.ProductDetailsResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.product.ProductListResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.product.ProductSubCategoryResponse
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

    suspend fun getProductCategoryResponse(
        type: String,
        productCategoryResponse: (ProductCategoryResponse) -> Unit
    ): MutableStateFlow<String?>

    suspend fun getProductSubCategoryResponse(
        categoryId: String,
        productSubCategoryResponse: (ProductSubCategoryResponse) -> Unit
    ): MutableStateFlow<String?>

    suspend fun getProductListResponse(
        page: Int,
        subCategoryId: String,
        productListResponse: (ProductListResponse) -> Unit
    ): MutableStateFlow<String?>

    suspend fun getProductDetailsResponse(
        productId: Int,
        productDetailsResponse: (ProductDetailsResponse) -> Unit
    ): MutableStateFlow<String?>

    suspend fun getCreateCartResponse(
        createCartBody: CreateCartBody,
        cartCreateResponse: (CartCreateResponse) -> Unit
    ): MutableStateFlow<String?>

    suspend fun getCartListResponse(
        cartListResponse: (CartListResponse) -> Unit
    ): MutableStateFlow<String?>

    suspend fun deleteCartItem(
        cartId: Int,
        deleteCartResponse: (DeleteCartResponse) -> Unit
    ): MutableStateFlow<String?>

    suspend fun getBookingResponse(
        bookingBody: BoatBookingBody,
        boatBookingResponse: (BoatBookingResponse) -> Unit
    ): MutableStateFlow<String?>
}
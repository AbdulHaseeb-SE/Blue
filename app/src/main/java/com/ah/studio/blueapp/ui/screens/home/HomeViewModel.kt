package com.ah.studio.blueapp.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategorySubCategoryResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.Category
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.SubCategory
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails.BoatDetailsResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boats.BoatBody
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boats.BoatResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.gallery.GalleryImageResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.timeSlot.AvailableTimeSlotResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.timeSlot.TimeSlotBody
import com.ah.studio.blueapp.ui.screens.home.domain.repository.IHomeRepository
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel(
    private val homeRepository: IHomeRepository
) : ViewModel(), IHomeViewModel {

    private val _boatCategorySubCategoryResponse =
        MutableStateFlow<BoatCategorySubCategoryResponse?>(null)
    override val boatCategorySubCategoryResponse: MutableStateFlow<BoatCategorySubCategoryResponse?>
        get() = _boatCategorySubCategoryResponse


    private val _categoryResponse = MutableStateFlow<List<Category>?>(null)
    override val categoryList: MutableStateFlow<List<Category>?>
        get() = _categoryResponse


    private val _subCategoryResponse = MutableStateFlow<List<SubCategory>?>(null)
    override val subCategoryList: MutableStateFlow<List<SubCategory>?>
        get() = _subCategoryResponse


    private val _boatResponse = MutableStateFlow<BoatResponse?>(null)
    override val boatResponse: MutableStateFlow<BoatResponse?>
        get() = _boatResponse

    private val _boatDetailsResponse = MutableStateFlow<BoatDetailsResponse?>(null)
    override val boatDetailsResponse: MutableStateFlow<BoatDetailsResponse?>
        get() = _boatDetailsResponse

    private val _galleryImagesResponse = MutableStateFlow<GalleryImageResponse?>(null)
    override val galleryImagesResponse: MutableStateFlow<GalleryImageResponse?>
        get() = _galleryImagesResponse

    private val _availableTimeSlotResponse = MutableStateFlow<AvailableTimeSlotResponse?>(null)
    override val availableTimeSlotResponse: MutableStateFlow<AvailableTimeSlotResponse?>
        get() = _availableTimeSlotResponse


    override fun getBoatCategoryResponse(): MutableStateFlow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getBoatCategorySubCategory { categoryResponse ->
                    _boatCategorySubCategoryResponse.value = categoryResponse
                    _categoryResponse.value = categoryResponse.data.category
                    _subCategoryResponse.value = categoryResponse.data.sub_category
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }

    override fun getBoatResponse(boatBody: BoatBody): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getBoatList(
                    boatBody = boatBody
                ) { boatResponse ->
                    _boatResponse.value = boatResponse
                    Log.d(
                        "CheckBoatResponse",
                        "boat response = ${boatResponse.data.size}"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckBoatResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }

    override fun getBoatDetailsResponse(boatId: Int): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getBoatDetailsResponse(
                    boatId = boatId
                ) { boatDetailsResponse ->
                    _boatDetailsResponse.value = boatDetailsResponse
                    Log.d(
                        "CheckBoatResponse",
                        "boat response = ${boatDetailsResponse.data.size}"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckBoatResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }

    override fun getGalleryImageResponse(boatId: Int): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getGalleryImagesResponse(
                    boatId = boatId
                ) { galleryImagesResponse ->
                    _galleryImagesResponse.value = galleryImagesResponse
                    Log.d(
                        "CheckBoatResponse",
                        "gallery response = ${galleryImagesResponse.data.size}"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckBoatResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }

    override fun getAvailableTimeSlotResponse(timeSlotBody: TimeSlotBody): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getAvailableTimeSlotResponse(
                    timeSlotBody = timeSlotBody
                ) { availableIimeSlotResponse ->
                    _availableTimeSlotResponse.value = availableIimeSlotResponse
                    Log.d(
                        "CheckBoatResponse",
                        "gallery response = $availableIimeSlotResponse"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckBoatResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }
}
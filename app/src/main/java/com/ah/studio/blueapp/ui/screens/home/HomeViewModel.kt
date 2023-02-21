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


    private val _productCategoryResponse = MutableStateFlow<ProductCategoryResponse?>(null)
    override val productCategoryResponse: MutableStateFlow<ProductCategoryResponse?>
        get() = _productCategoryResponse


    private val _productSubCategoryResponse = MutableStateFlow<ProductSubCategoryResponse?>(null)
    override val productSubCategoryResponse: MutableStateFlow<ProductSubCategoryResponse?>
        get() = _productSubCategoryResponse


    private val _productListResponse = MutableStateFlow<ProductListResponse?>(null)
    override val productListResponse: MutableStateFlow<ProductListResponse?>
        get() = _productListResponse


    private val _productDetailsResponse = MutableStateFlow<ProductDetailsResponse?>(null)
    override val productDetailsResponse: MutableStateFlow<ProductDetailsResponse?>
        get() = _productDetailsResponse


    private val _createCartResponse = MutableStateFlow<CartCreateResponse?>(null)
    override val createCartResponse: MutableStateFlow<CartCreateResponse?>
        get() = _createCartResponse


    private val _cartListResponse = MutableStateFlow<CartListResponse?>(null)
    override val cartListResponse: MutableStateFlow<CartListResponse?>
        get() = _cartListResponse


    private val _deleteCartItemResponse = MutableStateFlow<DeleteCartResponse?>(null)
    override val deleteCartItemResponse: MutableStateFlow<DeleteCartResponse?>
        get() = _deleteCartItemResponse


    private val _bookingResponse = MutableStateFlow<BoatBookingResponse?>(null)
    override val boatBookingResponse: MutableStateFlow<BoatBookingResponse?>
        get() = _bookingResponse


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
                ) { availableTimeSlotResponse ->
                    _availableTimeSlotResponse.value = availableTimeSlotResponse
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

    override fun getProductCategoryResponse(type: String): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getProductCategoryResponse(
                    type = type
                ) { productResponse ->
                    _productCategoryResponse.value = productResponse
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckProductResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckProductResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckProductResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }

    override fun getProductSubCategoryResponse(productCategoryId: String): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getProductSubCategoryResponse(
                    categoryId = productCategoryId
                ) { productSubCategoryResponse ->
                    _productSubCategoryResponse.value = productSubCategoryResponse
                    Log.d(
                        "CheckProductResponse",
                        "gallery response = $productSubCategoryResponse"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckProductResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckProductResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckProductResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }

    override fun getProductListResponse(page: Int, subCategoryId: String): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getProductListResponse(
                    page = page,
                    subCategoryId = subCategoryId
                ) { productListResponse ->
                    _productListResponse.value = productListResponse
                    Log.d(
                        "CheckProductListResponse",
                        "gallery response = $productListResponse"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckProductResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckProductResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckProductResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }

    override fun getProductDetailsResponse(productId: Int): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getProductDetailsResponse(
                    productId = productId
                ) { productDetailsResponse ->
                    _productDetailsResponse.value = productDetailsResponse
                    Log.d(
                        "CheckProductListResponse",
                        "gallery response = $productListResponse"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckProductResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckProductResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckProductResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }

    override fun getCartCreateResponse(cartBody: CreateCartBody): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getCreateCartResponse(
                    createCartBody = cartBody
                ) { cartResponse ->
                    _createCartResponse.value = cartResponse
                    Log.d(
                        "CheckCreateCartResponse",
                        "gallery response = $createCartResponse"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }

    override fun getCartListResponse(): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getCartListResponse { cartListResponse ->
                    _cartListResponse.value = cartListResponse
                    Log.d(
                        "CheckCreateCartResponse",
                        "gallery response = $cartListResponse"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }

    override fun getDeleteCartResponse(cartId: Int): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.deleteCartItem(cartId = cartId) { deleteCartResponse ->
                    _deleteCartItemResponse.value = deleteCartResponse
                    Log.d(
                        "CheckCreateCartResponse",
                        "gallery response = $deleteCartResponse"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }

    override fun getBoatBookingResponse(bookingBody: BoatBookingBody): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getBookingResponse(bookingBody = bookingBody) { boatBookingResponse ->
                    _bookingResponse.value = boatBookingResponse
                    Log.d(
                        "CheckCreateCartResponse",
                        "booking response = $boatBookingResponse"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }
}
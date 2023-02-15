package com.ah.studio.blueapp.ui.screens.home.domain.repository

import android.content.Context
import android.util.Log
import com.ah.studio.blueapp.api.VolleyInstance
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategorySubCategoryResponse
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
import com.ah.studio.blueapp.util.ApiConstants.AVAILABLE_TIMESLOTS_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.BOAT_DETAILS_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.BOAT_LIST_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.CATEGORY_SUBCATEGORY_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.GALLEY_IMAGES_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.PRODUCT_CATEGORY_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.PRODUCT_DETAILS_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.PRODUCT_LIST_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.PRODUCT_SUB_CATEGORY_ENDPOINT
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject

class HomeRepository(private val context: Context) : IHomeRepository {
    override suspend fun getBoatCategorySubCategory(
        boatCategorySubCategoryResponse: (BoatCategorySubCategoryResponse) -> Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)
        try {
            VolleyInstance(context).jsonObjectGetRequest(
                endPoint = CATEGORY_SUBCATEGORY_ENDPOINT,
                listener = { response ->
                    if (response.getString("status") == "200") {
                        boatCategorySubCategoryResponse(
                            Gson().fromJson(
                                response.toString(),
                                BoatCategorySubCategoryResponse::class.java
                            )
                        )
                        state.value = response.getString("status")
                    } else {
                        state.value = response.getString("status")
                    }
                },
                errorListener = { error ->
                    state.value = error.message?.ifEmpty { "false" }
                    Log.d(
                        "CheckResponse", "error status = $error"
                    )
                }
            )
        } catch (e: Exception) {
            state.value = e.message?.ifEmpty { "false" }
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
        return state
    }

    override suspend fun getBoatList(
        boatBody: BoatBody,
        boatList: (BoatResponse) -> Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)
        val jsonObject = JSONObject()
        jsonObject.put("category", boatBody.category)
        jsonObject.put("sub_category", boatBody.sub_category)
        jsonObject.put("page", boatBody.page)

        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = BOAT_LIST_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getString("status") == "200") {
                        boatList(
                            Gson().fromJson(
                                response.toString(),
                                BoatResponse::class.java
                            )
                        )
                        state.value = response.getString("status")
                    } else {
                        state.value = response.getString("status")
                    }
                },
                errorListener = { error ->
                    state.value = error.message?.ifEmpty { "false" }
                    Log.d(
                        "CheckResponse", "error status = $error"
                    )
                }
            )
        } catch (e: Exception) {
            state.value = e.message?.ifEmpty { "false" }
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
        return state
    }

    override suspend fun getBoatDetailsResponse(
        boatId: Int,
        boatDetailsResponse: (BoatDetailsResponse) -> Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)
        val jsonObject = JSONObject()
        jsonObject.put("id", boatId)

        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = BOAT_DETAILS_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getString("status") == "200") {
                        boatDetailsResponse(
                            Gson().fromJson(
                                response.toString(),
                                BoatDetailsResponse::class.java
                            )
                        )
                        state.value = response.getString("status")
                    } else {
                        state.value = response.getString("status")
                    }
                },
                errorListener = { error ->
                    state.value = error.message?.ifEmpty { "false" }
                    Log.d(
                        "CheckResponse", "error status = $error"
                    )
                }
            )
        } catch (e: Exception) {
            state.value = e.message?.ifEmpty { "false" }
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
        return state
    }

    override suspend fun getGalleryImagesResponse(
        boatId: Int,
        galleryImageResponse: (GalleryImageResponse) -> Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)
        val jsonObject = JSONObject()
        jsonObject.put("boat_id", boatId)

        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = GALLEY_IMAGES_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getString("status") == "200") {
                        galleryImageResponse(
                            Gson().fromJson(
                                response.toString(),
                                GalleryImageResponse::class.java
                            )
                        )
                        state.value = response.getString("status")
                    } else {
                        state.value = response.getString("status")
                    }
                },
                errorListener = { error ->
                    state.value = error.message?.ifEmpty { "false" }
                    Log.d(
                        "CheckResponse", "error status = $error"
                    )
                }
            )
        } catch (e: Exception) {
            state.value = e.message?.ifEmpty { "false" }
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
        return state
    }

    override suspend fun getAvailableTimeSlotResponse(
        timeSlotBody: TimeSlotBody,
        availableTimeSlotResponse: (AvailableTimeSlotResponse) -> Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)
        val jsonObject = JSONObject()
        jsonObject.put("start", timeSlotBody.start)
        jsonObject.put("end", timeSlotBody.end)
        jsonObject.put("boat_id", timeSlotBody.boat_id)

        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = AVAILABLE_TIMESLOTS_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getString("status") == "200") {
                        availableTimeSlotResponse(
                            Gson().fromJson(
                                response.toString(),
                                AvailableTimeSlotResponse::class.java
                            )
                        )
                        state.value = response.getString("status")
                    } else {
                        state.value = response.getString("status")
                    }
                },
                errorListener = { error ->
                    state.value = error.message?.ifEmpty { "false" }
                    Log.d(
                        "CheckResponse", "error status = $error"
                    )
                }
            )
        } catch (e: Exception) {
            state.value = e.message?.ifEmpty { "false" }
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
        return state
    }

    override suspend fun getProductCategoryResponse(
        type: String,
        productCategoryResponse: (ProductCategoryResponse) -> Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)
        val jsonObject = JSONObject()
        jsonObject.put("type", type)

        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = PRODUCT_CATEGORY_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getString("status") == "200") {
                        productCategoryResponse(
                            Gson().fromJson(
                                response.toString(),
                                ProductCategoryResponse::class.java
                            )
                        )
                        state.value = response.getString("status")
                    } else {
                        state.value = response.getString("status")
                    }
                },
                errorListener = { error ->
                    state.value = error.message?.ifEmpty { "false" }
                    Log.d(
                        "CheckResponse", "error status = $error"
                    )
                }
            )
        } catch (e: Exception) {
            state.value = e.message?.ifEmpty { "false" }
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
        return state
    }

    override suspend fun getProductSubCategoryResponse(
        categoryId: String,
        productSubCategoryResponse: (ProductSubCategoryResponse) -> Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)
        val jsonObject = JSONObject()
        jsonObject.put("category_id", categoryId)

        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = PRODUCT_SUB_CATEGORY_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getString("status") == "200") {
                        productSubCategoryResponse(
                            Gson().fromJson(
                                response.toString(),
                                ProductSubCategoryResponse::class.java
                            )
                        )
                        state.value = response.getString("status")
                    } else {
                        state.value = response.getString("status")
                    }
                },
                errorListener = { error ->
                    state.value = error.message?.ifEmpty { "false" }
                    Log.d(
                        "CheckResponse", "error status = $error"
                    )
                }
            )
        } catch (e: Exception) {
            state.value = e.message?.ifEmpty { "false" }
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
        return state
    }

    override suspend fun getProductListResponse(
        page: Int,
        subCategoryId: String,
        productListResponse: (ProductListResponse) -> Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)
        val jsonObject = JSONObject()
        jsonObject.put("page", page)
        jsonObject.put("sub_category_id", subCategoryId)

        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = PRODUCT_LIST_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getString("status") == "200") {
                        productListResponse(
                            Gson().fromJson(
                                response.toString(),
                                ProductListResponse::class.java
                            )
                        )
                        state.value = response.getString("status")
                    } else {
                        state.value = response.getString("status")
                    }
                },
                errorListener = { error ->
                    state.value = error.message?.ifEmpty { "false" }
                    Log.d(
                        "CheckResponse", "error status = $error"
                    )
                }
            )
        } catch (e: Exception) {
            state.value = e.message?.ifEmpty { "false" }
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
        return state
    }

    override suspend fun getProductDetailsResponse(
        productId: Int,
        productDetailsResponse: (ProductDetailsResponse)->Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)
        val jsonObject = JSONObject()
        jsonObject.put("id", productId)

        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = PRODUCT_DETAILS_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getString("status") == "200") {
                        productDetailsResponse(
                            Gson().fromJson(
                                response.toString(),
                                ProductDetailsResponse::class.java
                            )
                        )
                        state.value = response.getString("status")
                    } else {
                        state.value = response.getString("status")
                    }
                },
                errorListener = { error ->
                    state.value = error.message?.ifEmpty { "false" }
                    Log.d(
                        "CheckResponse", "error status = $error"
                    )
                }
            )
        } catch (e: Exception) {
            state.value = e.message?.ifEmpty { "false" }
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
        return state
    }
}

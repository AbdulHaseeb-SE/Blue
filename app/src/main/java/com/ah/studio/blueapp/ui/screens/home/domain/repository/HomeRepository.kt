package com.ah.studio.blueapp.ui.screens.home.domain.repository

import android.content.Context
import android.util.Log
import com.ah.studio.blueapp.api.VolleyInstance
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategorySubCategoryResponse
import com.ah.studio.blueapp.util.ApiConstants.CATEGORY_SUBCATEGORY_ENDPOINT
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow

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
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
        return state
    }
}
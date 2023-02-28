package com.ah.studio.blueapp.ui.screens.seafarer.domain.repository

import android.content.Context
import android.util.Log
import com.ah.studio.blueapp.api.VolleyInstance
import com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerCategory.SeafarerCategoryResponse
import com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerList.SeafarerListResponse
import com.ah.studio.blueapp.util.ApiConstants.SEAFARER_CATEGORY_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.SEAFARER_LIST_ENDPOINT
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject

class SeafarerRepository(private val context: Context) : ISeafarerRepository {
    override fun getSeafarerListResponse(
        page: Int,
        category: String,
        seafarerListResponse: (SeafarerListResponse) -> Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)
        val jsonObject = JSONObject()
        jsonObject.put("page", page)
        jsonObject.put("category", category)

        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = SEAFARER_LIST_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    seafarerListResponse(
                        Gson().fromJson(
                            response.toString(),
                            SeafarerListResponse::class.java
                        )
                    )
                    state.value = response.getString("status")
                }
            ) { error ->
                state.value = error.message?.ifEmpty { "false" }
                Log.d(
                    "CheckResponse", "error status = $error"
                )
            }
        } catch (e: Exception) {
            state.value = e.message?.ifEmpty { "false" }
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
        return state
    }

    override fun getSeafarerCategoryResponse(seafarerCategoryResponse: (SeafarerCategoryResponse) -> Unit): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)
        try {
            VolleyInstance(context).jsonObjectGetRequest(
                endPoint = SEAFARER_CATEGORY_ENDPOINT,
                listener = { response ->
                    seafarerCategoryResponse(
                        Gson().fromJson(
                            response.toString(),
                            SeafarerCategoryResponse::class.java
                        )
                    )
                    state.value = response.getString("status")
                }
            ) { error ->
                state.value = error.message?.ifEmpty { "false" }
                Log.d(
                    "CheckResponse", "error status = $error"
                )
            }
        } catch (e: Exception) {
            state.value = e.message?.ifEmpty { "false" }
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
        return state
    }
}
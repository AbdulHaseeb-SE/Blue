package com.ah.studio.blueapp.ui.screens.myBooking.domain.repository

import android.content.Context
import android.util.Log
import com.ah.studio.blueapp.api.VolleyInstance
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.BoatBookingListResponse
import com.ah.studio.blueapp.util.ApiConstants.BOAT_BOOKING_LIST_ENDPOINT
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow

class MyBookingRepository(private val context: Context) : IMyBookingRepository {


    override fun getBoatBookingList(
        bookingListResponse: (BoatBookingListResponse) -> Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)
        try {
            VolleyInstance(context).jsonObjectGetRequest(
                endPoint = BOAT_BOOKING_LIST_ENDPOINT,
                listener = { response ->
                    if (response.getString("status") == "200") {
                        bookingListResponse(
                            Gson().fromJson(
                                response.toString(),
                                BoatBookingListResponse::class.java
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
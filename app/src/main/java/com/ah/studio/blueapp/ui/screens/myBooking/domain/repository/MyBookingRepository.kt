package com.ah.studio.blueapp.ui.screens.myBooking.domain.repository

import android.content.Context
import android.util.Log
import com.ah.studio.blueapp.api.VolleyInstance
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatDetails.BookedBoatDetailsResponse
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatList.BoatBookingListResponse
import com.ah.studio.blueapp.util.ApiConstants.BOAT_BOOKING_LIST_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.BOOKED_BOAT_DETAILS_ENDPOINT
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject
import java.nio.charset.Charset

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

    override fun getBookedBoatDetailResponse(id:String, bookedBoatDetailsResponse: (BookedBoatDetailsResponse) -> Unit) {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = BOOKED_BOAT_DETAILS_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getInt("status") == 200) {
                        bookedBoatDetailsResponse(
                            Gson().fromJson(
                                response.toString(),
                                BookedBoatDetailsResponse::class.java
                            )
                        )
                    } else {
                        bookedBoatDetailsResponse(
                            BookedBoatDetailsResponse(
                                message = response.getString("message"),
                                status = response.getInt("status"),
                                data = null,
                                type = ""
                            )
                        )
                    }
                },
                errorListener = { error ->
                    val responseCode = error.networkResponse?.statusCode ?: -1
                    val responseBody =
                        error.networkResponse?.data?.toString(Charset.defaultCharset()) ?: ""
                    try {
                        val errorJson = JSONObject(responseBody)
                        bookedBoatDetailsResponse(
                            BookedBoatDetailsResponse(
                                message = errorJson.getString("message"),
                                status = errorJson.getInt("status"),
                                data = null,
                                type = ""
                            )
                        )
                    } catch (e: Exception) {
                        BookedBoatDetailsResponse(
                            message = "Error: $responseCode $responseBody",
                            status = 400,
                            data = null,
                            type = ""
                        )
                    }
                }
            )
        } catch (e: Exception) {
            Log.e(
                "CheckResponse",
                "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
    }
}
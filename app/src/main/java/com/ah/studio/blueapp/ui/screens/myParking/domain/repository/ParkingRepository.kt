package com.ah.studio.blueapp.ui.screens.myParking.domain.repository

import android.content.Context
import android.util.Log
import com.ah.studio.blueapp.api.VolleyInstance
import com.ah.studio.blueapp.ui.screens.myParking.domain.dto.myParkingBooking.MyParkingBookingResponse
import com.ah.studio.blueapp.ui.screens.myParking.domain.dto.parkingList.ParkingListResponse
import com.ah.studio.blueapp.util.ApiConstants.MY_PARKING_BOOKING_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.PARKING_LIST_ENDPOINT
import com.google.gson.Gson
import org.json.JSONObject
import java.nio.charset.Charset

class ParkingRepository(private val context: Context) : IParkingRepository {
    override fun getMyParkingBookingList(
        page: Int,
        myParkingBookingResponse: (MyParkingBookingResponse) -> Unit
    ) {
        val jsonObject = JSONObject()
        jsonObject.put("page", page)
        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = MY_PARKING_BOOKING_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getInt("status") == 200) {
                        myParkingBookingResponse(
                            Gson().fromJson(
                                response.toString(),
                                MyParkingBookingResponse::class.java
                            )
                        )
                    } else {
                        myParkingBookingResponse(
                            MyParkingBookingResponse(
                                message = response.getString("message"),
                                status = response.getInt("status"),
                                data = listOf(),
                                last_page = response.getInt("last_page"),
                                total = response.getInt("total"),
                                page = response.getInt("page"),
                                type = response.getString("type")
                            )
                        )
                    }
                },
                errorListener = { error ->
                    val responseCode = error.networkResponse?.statusCode ?: -1
                    val responseBody =
                        error.networkResponse?.data?.toString(Charset.defaultCharset()) ?: ""
                    Log.d(
                        "CheckResponse",
                        "error status code = $responseCode, response body = $responseBody"
                    )
                    try {
                        val errorJson = JSONObject(responseBody)
                        myParkingBookingResponse(
                            MyParkingBookingResponse(
                                message = errorJson.getString("message"),
                                status = errorJson.getInt("status"),
                                data = listOf(),
                                last_page = errorJson.getInt("last_page"),
                                total = errorJson.getInt("total"),
                                page = errorJson.getInt("page"),
                                type = errorJson.getString("type")
                            )
                        )
                        Log.d(
                            "CheckResponse",
                            "message = ${errorJson.getString("message")},\n" +
                                    "success = ${errorJson.getBoolean("success")}"
                        )
                    } catch (e: Exception) {
                        MyParkingBookingResponse(
                            message = "Error: $responseCode $responseBody",
                            status = 404,
                            data = listOf(),
                            last_page = 0,
                            total = 0,
                            page = 0,
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

    override fun getParkingList(
        page: Int,
        myParkingListResponse: (ParkingListResponse) -> Unit
    ) {
        val jsonObject = JSONObject()
        jsonObject.put("page", page)
        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = PARKING_LIST_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getInt("status") == 200) {
                        myParkingListResponse(
                            Gson().fromJson(
                                response.toString(),
                                ParkingListResponse::class.java
                            )
                        )
                    } else {
                        myParkingListResponse(
                            ParkingListResponse(
                                message = response.getString("message"),
                                status = response.getInt("status"),
                                data = listOf(),
                                last_page = response.getInt("last_page"),
                                total = response.getInt("total"),
                                page = response.getInt("page"),
                                type = response.getString("type")
                            )
                        )
                    }
                },
                errorListener = { error ->
                    val responseCode = error.networkResponse?.statusCode ?: -1
                    val responseBody =
                        error.networkResponse?.data?.toString(Charset.defaultCharset()) ?: ""
                    Log.d(
                        "CheckResponse",
                        "error status code = $responseCode, response body = $responseBody"
                    )
                    try {
                        val errorJson = JSONObject(responseBody)
                        myParkingListResponse(
                            ParkingListResponse(
                                message = errorJson.getString("message"),
                                status = errorJson.getInt("status"),
                                data = listOf(),
                                last_page = errorJson.getInt("last_page"),
                                total = errorJson.getInt("total"),
                                page = errorJson.getInt("page"),
                                type = errorJson.getString("type")
                            )
                        )
                        Log.d(
                            "CheckResponse",
                            "message = ${errorJson.getString("message")},\n" +
                                    "success = ${errorJson.getBoolean("success")}"
                        )
                    } catch (e: Exception) {
                        ParkingListResponse(
                            message = "Error: $responseCode $responseBody",
                            status = 404,
                            data = listOf(),
                            last_page = 0,
                            total = 0,
                            page = 0,
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
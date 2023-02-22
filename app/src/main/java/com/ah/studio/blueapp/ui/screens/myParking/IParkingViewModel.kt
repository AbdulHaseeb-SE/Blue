package com.ah.studio.blueapp.ui.screens.myParking

import com.ah.studio.blueapp.ui.screens.myParking.domain.dto.myParkingBooking.MyParkingBookingResponse
import com.ah.studio.blueapp.ui.screens.myParking.domain.dto.parkingList.ParkingListResponse
import kotlinx.coroutines.flow.Flow

interface IParkingViewModel {
    fun getParkingBookingListResponse(page: Int)
    fun getParkingListResponse(page: Int)

    val parkingBookingListResponse: Flow<MyParkingBookingResponse?>
    val boatsAvailableToParkResponse: Flow<ParkingListResponse?>
}
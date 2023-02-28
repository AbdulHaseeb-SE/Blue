package com.ah.studio.blueapp.ui.screens.myParking.domain.repository

import com.ah.studio.blueapp.ui.screens.myParking.domain.dto.myParkingBooking.MyParkingBookingResponse
import com.ah.studio.blueapp.ui.screens.myParking.domain.dto.parkingList.ParkingListResponse

interface IParkingRepository {
    fun getMyParkingBookingList(
        page: Int,
        myParkingBookingResponse: (MyParkingBookingResponse) -> Unit
    )

    fun getParkingList(
        page: Int,
        myParkingListResponse: (ParkingListResponse) -> Unit
    )
}
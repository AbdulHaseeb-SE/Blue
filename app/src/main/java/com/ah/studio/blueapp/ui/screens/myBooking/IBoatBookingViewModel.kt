package com.ah.studio.blueapp.ui.screens.myBooking

import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatDetails.BookedBoatDetailsResponse
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatList.BoatBookingListResponse
import kotlinx.coroutines.flow.Flow

interface IBoatBookingViewModel {
    fun getBoatBookingListResponse()
    val boatBookingListResponse: Flow<BoatBookingListResponse?>
    fun getBookedBoatDetailResponse(id: String)
    val bookedBoatDetailsResponse: Flow<BookedBoatDetailsResponse?>
}
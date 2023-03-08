package com.ah.studio.blueapp.ui.screens.myBooking

import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatDetails.BookedBoatDetailsResponse
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatList.BoatBookingListResponse
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.review.AddReviewBody
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.review.AddReviewResponse
import kotlinx.coroutines.flow.Flow

interface IBoatBookingViewModel {
    fun getBoatBookingListResponse()
    val boatBookingListResponse: Flow<BoatBookingListResponse?>
    fun getBookedBoatDetailResponse(id: String)
    val bookedBoatDetailsResponse: Flow<BookedBoatDetailsResponse?>
    fun getAddReviewResponse(addReviewBody: AddReviewBody)
    val addReviewResponse: Flow<AddReviewResponse?>
}
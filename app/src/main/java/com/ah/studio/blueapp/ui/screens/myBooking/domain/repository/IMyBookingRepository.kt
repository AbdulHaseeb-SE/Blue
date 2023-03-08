package com.ah.studio.blueapp.ui.screens.myBooking.domain.repository

import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatDetails.BookedBoatDetailsResponse
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatList.BoatBookingListResponse
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.review.AddReviewBody
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.review.AddReviewResponse
import kotlinx.coroutines.flow.MutableStateFlow

interface IMyBookingRepository {
    fun getBoatBookingList(
        bookingListResponse: (BoatBookingListResponse) -> Unit
    ): MutableStateFlow<String?>

    fun getBookedBoatDetailResponse(
        id: String,
        bookedBoatDetailsResponse: (
            BookedBoatDetailsResponse
        ) -> Unit
    )
    fun getAddReviewResponse(
        addReviewBody: AddReviewBody,
        addReviewResponse: (
            AddReviewResponse
        ) -> Unit
    )
}
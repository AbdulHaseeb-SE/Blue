package com.ah.studio.blueapp.ui.screens.myBooking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatDetails.BookedBoatDetailsResponse
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatList.BoatBookingListResponse
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.review.AddReviewBody
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.review.AddReviewResponse
import com.ah.studio.blueapp.ui.screens.myBooking.domain.repository.IMyBookingRepository
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class BoatBookingViewModel(
    private val repository: IMyBookingRepository
) : ViewModel(), IBoatBookingViewModel {

    private val _boatBookingListResponse = MutableStateFlow<BoatBookingListResponse?>(null)
    override val boatBookingListResponse: Flow<BoatBookingListResponse?>
        get() = _boatBookingListResponse

    private val _bookedBoatDetailsResponse = MutableStateFlow<BookedBoatDetailsResponse?>(null)
    override val bookedBoatDetailsResponse: Flow<BookedBoatDetailsResponse?>
        get() = _bookedBoatDetailsResponse

    private val _addReviewResponse = MutableStateFlow<AddReviewResponse?>(null)
    override val addReviewResponse: Flow<AddReviewResponse?>
        get() = _addReviewResponse

    override fun getBoatBookingListResponse() {
        var response = MutableStateFlow<String?>("")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = repository.getBoatBookingList { boatBookingListResponse ->
                    _boatBookingListResponse.value = boatBookingListResponse
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCategoryResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
    }


    override fun getBookedBoatDetailResponse(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getBookedBoatDetailResponse(id) { response ->
                    _bookedBoatDetailsResponse.value = response
                }
            } catch (e: IOException) {
                Log.d(
                    "Response",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                Log.d(
                    "Response",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                Log.d(
                    "Response",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
    }

    override fun getAddReviewResponse(addReviewBody: AddReviewBody) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getAddReviewResponse(addReviewBody) { response ->
                    _addReviewResponse.value = response
                }
            } catch (e: IOException) {
                Log.d(
                    "Response",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                Log.d(
                    "Response",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                Log.d(
                    "Response",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
    }
}

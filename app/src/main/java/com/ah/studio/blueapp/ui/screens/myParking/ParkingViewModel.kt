package com.ah.studio.blueapp.ui.screens.myParking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ah.studio.blueapp.ui.screens.myParking.domain.dto.myParkingBooking.MyParkingBookingResponse
import com.ah.studio.blueapp.ui.screens.myParking.domain.dto.parkingList.ParkingListResponse
import com.ah.studio.blueapp.ui.screens.myParking.domain.repository.IParkingRepository
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ParkingViewModel(private val repository: IParkingRepository) : ViewModel(),
    IParkingViewModel {

    private val _parkingBookingListResponse = MutableStateFlow<MyParkingBookingResponse?>(null)
    override val parkingBookingListResponse: Flow<MyParkingBookingResponse?>
        get() = _parkingBookingListResponse


    private val _boatsAvailableToParkResponse = MutableStateFlow<ParkingListResponse?>(null)
    override val boatsAvailableToParkResponse: Flow<ParkingListResponse?>
        get() = _boatsAvailableToParkResponse


    override fun getParkingBookingListResponse(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getMyParkingBookingList(page) { response ->
                    _parkingBookingListResponse.value = response
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

    override fun getParkingListResponse(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getParkingList(page) { response ->
                    _boatsAvailableToParkResponse.value = response
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


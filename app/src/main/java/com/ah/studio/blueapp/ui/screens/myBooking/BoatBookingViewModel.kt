package com.ah.studio.blueapp.ui.screens.myBooking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ah.studio.blueapp.ui.screens.myBooking.domain.IBoatBookingViewModel
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.BoatBookingListResponse
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

    override fun getBoatBookingListResponse(){
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
}

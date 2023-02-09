package com.ah.studio.blueapp.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategoryResponse
import com.ah.studio.blueapp.ui.screens.home.domain.repository.IHomeRepository
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: IHomeRepository
) : ViewModel(), IHomeViewModel {


    private val _boatCategoryResponse = MutableStateFlow<BoatCategoryResponse?>(null)
    override val boatCategoryResponse: MutableStateFlow<BoatCategoryResponse?>
        get() = _boatCategoryResponse

    override fun getBoatCategoryResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = homeRepository.getBoatCategory()
            if (response.isSuccessful) {
                try {
                    val responseBody = response.body()
                    val json = responseBody?.string()
                    _boatCategoryResponse.value =
                        Gson().fromJson(json, BoatCategoryResponse::class.java)
                    Log.d(
                        "CheckBoatCategory",
                        boatCategoryResponse.value?.data?.category?.size.toString()
                    )

                } catch (e: JsonSyntaxException) {
                    // Handle the error here
                    Log.d("CheckBoatCategory", "exceptionMessage = ${e.message} ${e.cause}")
                }
            } else {
                // Handle the error here
            }

            /*  val response = homeRepository.getBoatCategory()
              _boatCategoryResponse.value = response.body()
              Log.d("CheckBoatCategory", boatCategoryResponse.value.toString())
              Log.d("CheckBoatCategory", response.body()?.data?.category?.size.toString())*/
        }
    }


}
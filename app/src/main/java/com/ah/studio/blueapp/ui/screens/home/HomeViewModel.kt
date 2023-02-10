package com.ah.studio.blueapp.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategorySubCategoryResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.Category
import com.ah.studio.blueapp.ui.screens.home.domain.repository.IHomeRepository
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class HomeViewModel(
    private val homeRepository: IHomeRepository
) : ViewModel(), IHomeViewModel {

    private val _boatCategorySubCategoryResponse =
        MutableStateFlow<BoatCategorySubCategoryResponse?>(null)
    override val boatCategorySubCategoryResponse: MutableStateFlow<BoatCategorySubCategoryResponse?>
        get() = _boatCategorySubCategoryResponse


    private val _categoryResponse = MutableStateFlow<List<Category>?>(null)
    override val categoryList: MutableStateFlow<List<Category>?>
        get() = _categoryResponse


    override fun getBoatCategoryResponse(): MutableStateFlow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = homeRepository.getBoatCategorySubCategory { categoryResponse ->
                    _boatCategorySubCategoryResponse.value = categoryResponse
                    _categoryResponse.value = categoryResponse.data.category
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
        return response
    }
}
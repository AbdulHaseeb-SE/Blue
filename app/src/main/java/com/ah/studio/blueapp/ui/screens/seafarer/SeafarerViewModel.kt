package com.ah.studio.blueapp.ui.screens.seafarer

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerCategory.SeafarerCategoryResponse
import com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerList.SeafarerListResponse
import com.ah.studio.blueapp.ui.screens.seafarer.domain.repository.ISeafarerRepository
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class SeafarerViewModel(
    private val repository: ISeafarerRepository
) : ViewModel(), ISeafarerViewModel {
    private val _seafarerListResponse = MutableStateFlow<SeafarerListResponse?>(null)
    override val seafarerListResponse: Flow<SeafarerListResponse?>
        get() = _seafarerListResponse

    private val _seafarerCategoryResponse = MutableStateFlow<SeafarerCategoryResponse?>(null)
    override val seafarerCategoryResponse: Flow<SeafarerCategoryResponse?>
        get() = _seafarerCategoryResponse

    override fun getSeafarerListResponse(
        page: Int,
        category: String
    ): Flow<String?> {
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = repository.getSeafarerListResponse(
                    page = page,
                    category = category
                ) { seafarerListResponse ->
                    _seafarerListResponse.value = seafarerListResponse
                    Log.d(
                        "CheckCreateCartResponse",
                        "gallery response = $seafarerListResponse"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckCreateCartResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
        return response
    }

    override fun getSeafarerCategoryResponse(){
        var response = MutableStateFlow<String?>(null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                response = repository.getSeafarerCategoryResponse { seafarerCategoryResponse ->
                    _seafarerCategoryResponse.value = seafarerCategoryResponse
                    Log.d(
                        "CheckResponse",
                        "seafarerCategoryResponse = $seafarerListResponse"
                    )
                }
            } catch (e: IOException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckResponse",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckResponse",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                response.value = e.message.toString()
                Log.d(
                    "CheckResponse",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }  }
}

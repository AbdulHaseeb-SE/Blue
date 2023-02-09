package com.ah.studio.blueapp.ui.screens.home.domain.repository

import android.content.Context
import com.ah.studio.blueapp.api.RetrofitInstance
import okhttp3.ResponseBody
import retrofit2.Response

class HomeRepository(private val context: Context) : IHomeRepository {
    override suspend fun getBoatCategory(): Response<ResponseBody> {
        return RetrofitInstance(context).api.getBoatCategories()
    }


}
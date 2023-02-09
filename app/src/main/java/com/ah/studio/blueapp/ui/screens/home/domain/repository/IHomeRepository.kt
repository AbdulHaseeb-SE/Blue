package com.ah.studio.blueapp.ui.screens.home.domain.repository

import okhttp3.ResponseBody
import retrofit2.Response

interface IHomeRepository {
    suspend fun getBoatCategory(): Response<ResponseBody>
}
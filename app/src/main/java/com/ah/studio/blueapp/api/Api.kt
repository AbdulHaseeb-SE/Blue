package com.ah.studio.blueapp.api

import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.LoginCredentials
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.UserLoginResponse
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.User
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.UserRegistrationResponse
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.BoatCategoryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @POST("register")
    suspend fun registerUser(
        @Body register: User
    ): Response<UserRegistrationResponse>

    @POST("login")
    suspend fun validateUser(
        @Body loginCredentials: LoginCredentials
    ): Response<UserLoginResponse>

    @GET("category-subcategory")
    suspend fun getBoatCategories(): BoatCategoryResponse

}
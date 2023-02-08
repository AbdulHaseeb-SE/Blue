package com.ah.studio.blueapp.ui.screens.authentication.domain.repository

import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.LoginCredentials
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.UserLoginResponse
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.User
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.UserRegistrationResponse
import retrofit2.Response

interface IUserRepository {
   suspend fun registerUser(registerUserResponse: User): Response<UserRegistrationResponse>

   suspend fun validateUser(loginCredentials: LoginCredentials) : Response<UserLoginResponse>
}
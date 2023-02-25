package com.ah.studio.blueapp.ui.screens.authentication.domain.repository

import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.LoginCredentials
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.UserLoginResponse
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.User
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.UserRegistrationResponse

interface IUserRepository {
    suspend fun registerUser(
        userDetails: User,
        userRegistrationResponse: (UserRegistrationResponse) -> Unit
    )

    suspend fun validateUserRequest(
        loginCredentials: LoginCredentials,
        userLoginResponseBody: (UserLoginResponse) -> Unit
    )
}
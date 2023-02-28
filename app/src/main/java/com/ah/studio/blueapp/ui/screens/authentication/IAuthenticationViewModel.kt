package com.ah.studio.blueapp.ui.screens.authentication

import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.LoginCredentials
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.UserLoginResponse
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.User
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.UserRegistrationResponse
import kotlinx.coroutines.flow.Flow

interface IAuthenticationViewModel {

    fun registerUserResponse(userDetails: User)
    fun loginUserResponse(loginCredentials: LoginCredentials)

    val registerResponse: Flow<UserRegistrationResponse?>
    val validateUserResponse: Flow<UserLoginResponse?>


    abstract fun handleError(throwable: Throwable)

}
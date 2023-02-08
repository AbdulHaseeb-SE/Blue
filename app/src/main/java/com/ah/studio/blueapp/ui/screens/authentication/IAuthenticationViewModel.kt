package com.ah.studio.blueapp.ui.screens.authentication

import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.LoginCredentials
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.UserLoginResponse
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.User
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.UserRegistrationResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IAuthenticationViewModel {

    val registerResponse: Flow<Response<UserRegistrationResponse>?>
    fun registerUserResponse(
        registerUserResponse: User
    )
    val validateUserResponse: Flow<Response<UserLoginResponse>?>
    fun loginUserResponse(
        loginCredentials: LoginCredentials
    )



    abstract fun handleError(throwable: Throwable)

}
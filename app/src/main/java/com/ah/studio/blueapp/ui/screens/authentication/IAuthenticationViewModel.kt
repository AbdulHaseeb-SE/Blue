package com.ah.studio.blueapp.ui.screens.authentication

import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.LoginCredentials
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.UserLoginResponse
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.User
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.UserRegistrationResponse
import kotlinx.coroutines.flow.Flow

interface IAuthenticationViewModel {

    val registerResponse: Flow<UserRegistrationResponse?>
    fun registerUserResponse(userDetails: User): Flow<String?>

    val validateUserResponse: Flow<UserLoginResponse?>
    fun loginUserResponse(loginCredentials: LoginCredentials): Flow<String?>


    abstract fun handleError(throwable: Throwable)

}
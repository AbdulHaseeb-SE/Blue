package com.ah.studio.blueapp.ui.screens.authentication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ah.studio.blueapp.SessionManager
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.LoginCredentials
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.UserLoginResponse
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.User
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.UserRegistrationResponse
import com.ah.studio.blueapp.ui.screens.authentication.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class AuthenticationViewModel(
    private val repository: IUserRepository,
    private val sessionManager: SessionManager
) : ViewModel(), IAuthenticationViewModel {


    private val _registerResponse = MutableStateFlow<Response<UserRegistrationResponse>?>(null)
    override val registerResponse: MutableStateFlow<Response<UserRegistrationResponse>?>
        get() = _registerResponse

    override fun registerUserResponse(registerUserResponse: User) {
        viewModelScope.launch {
            try {
                val response = repository.registerUser(registerUserResponse)
                if (response.isSuccessful) {
                    sessionManager.saveToken(response.body()?.data?.token ?: "")
                }
                _registerResponse.value = response
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private val _validationResponse = MutableStateFlow<Response<UserLoginResponse>?>(null)
    override val validateUserResponse: Flow<Response<UserLoginResponse>?>
        get() = _validationResponse

    override fun loginUserResponse(loginCredentials: LoginCredentials) {
        viewModelScope.launch {
            try {
                val response = repository.validateUser(loginCredentials)
                if (response.isSuccessful) {
                    sessionManager.saveToken(response.body()?.data?.token ?: "")
                }
                _validationResponse.value = response
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    override fun handleError(throwable: Throwable) {
        Log.d(
            "CheckCrash",
            throwable.message.toString()
                    + throwable.cause
                    + throwable.localizedMessage
        )
    }
}
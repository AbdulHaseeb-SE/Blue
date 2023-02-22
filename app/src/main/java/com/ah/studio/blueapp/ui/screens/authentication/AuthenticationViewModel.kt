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

class AuthenticationViewModel(
    private val repository: IUserRepository,
    private val sessionManager: SessionManager
) : ViewModel(), IAuthenticationViewModel {

    /********************************** User Registration Section *********************************************************/

    private val _registerResponse = MutableStateFlow<UserRegistrationResponse?>(null)
    override val registerResponse: MutableStateFlow<UserRegistrationResponse?>
        get() = _registerResponse

    override fun registerUserResponse(userDetails: User): MutableStateFlow<String?> {
        var response = MutableStateFlow<String?>(null)
        viewModelScope.launch {
            try {
                response = repository.registerUser(
                    userDetails = userDetails,
                    userRegistrationResponse = { userRegistrationResponse ->
                        _registerResponse.value = userRegistrationResponse
                    }
                )
            } catch (e: Exception) {
                response.value = e.localizedMessage
                handleError(e)
            }
        }
        return response
    }

    /*************************************  User Login Validation **********************************************************/

    private val _validationResponse = MutableStateFlow<UserLoginResponse?>(null)
    override val validateUserResponse: Flow<UserLoginResponse?>
        get() = _validationResponse

    override fun loginUserResponse(loginCredentials: LoginCredentials): MutableStateFlow<String?> {
        var response = MutableStateFlow<String?>(null)
        viewModelScope.launch {
            try {
                response = repository.validateUserRequest(
                    loginCredentials,
                    userLoginResponseBody = { userLoginResponse ->
                        _validationResponse.value = userLoginResponse
                        sessionManager.saveToken(userLoginResponse.data.token)
                        sessionManager.saveRole(userLoginResponse.data.role)
                        Log.d("CheckToken", "token = " + sessionManager.getToken().toString())
                    }
                )
            } catch (e: Exception) {
                response.value = e.localizedMessage
                handleError(e)
            }
        }
        return response
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
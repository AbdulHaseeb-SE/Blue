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
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AuthenticationViewModel(
    private val repository: IUserRepository,
    private val sessionManager: SessionManager
) : ViewModel(), IAuthenticationViewModel {

    /********************************** User Registration Section *********************************************************/

    private val _registerResponse = MutableStateFlow<UserRegistrationResponse?>(null)
    override val registerResponse: MutableStateFlow<UserRegistrationResponse?>
        get() = _registerResponse

    override fun registerUserResponse(userDetails: User){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.registerUser(
                    userDetails = userDetails,
                    userRegistrationResponse = { userRegistrationResponse ->
                        _registerResponse.value = userRegistrationResponse
                    }
                )
            } catch (e: IOException) {
                Log.d(
                    "Response",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                Log.d(
                    "Response",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                Log.d(
                    "Response",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
            }
        }
    }

    /*************************************  User Login Validation **********************************************************/

    private val _validationResponse = MutableStateFlow<UserLoginResponse?>(null)
    override val validateUserResponse: Flow<UserLoginResponse?>
        get() = _validationResponse

    override fun loginUserResponse(loginCredentials: LoginCredentials) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.validateUserRequest(
                    loginCredentials,
                    userLoginResponseBody = { response ->
                        _validationResponse.value = response
                        if (response.data != null) {
                            sessionManager.saveToken(response.data.token)
                            sessionManager.saveRole(response.data.role)
                        }
                        Log.d("CheckToken", "token = " + sessionManager.getToken().toString())
                    }
                )
            } catch (e: IOException) {
                Log.d(
                    "Response",
                    "IOException, ${e.message.toString()}"
                )
                return@launch
            } catch (e: HttpException) {
                Log.d(
                    "Response",
                    "HttpException, unexpected response"
                )
                return@launch
            } catch (e: JsonSyntaxException) {
                Log.d(
                    "Response",
                    "JsonSyntaxException: ${e.message.toString()}"
                )
                return@launch
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
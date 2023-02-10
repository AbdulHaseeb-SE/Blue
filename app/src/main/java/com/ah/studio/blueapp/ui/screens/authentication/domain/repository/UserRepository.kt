package com.ah.studio.blueapp.ui.screens.authentication.domain.repository

import android.content.Context
import android.util.Log
import com.ah.studio.blueapp.api.VolleyInstance
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.LoginCredentials
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.UserLoginResponse
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.User
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.UserRegistrationResponse
import com.ah.studio.blueapp.util.ApiConstants
import com.ah.studio.blueapp.util.ApiConstants.REGISTER_ENDPOINT
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject

class UserRepository(private val context: Context) : IUserRepository {
    override suspend fun registerUser(
        userDetails: User,
        userRegistrationResponse: (UserRegistrationResponse) -> Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)

        val jsonObject = JSONObject()
        jsonObject.put("first_name", userDetails.first_name)
        jsonObject.put("last_name", userDetails.last_name)
        jsonObject.put("phone_no", userDetails.phone_no)
        jsonObject.put("email", userDetails.email)
        jsonObject.put("password", userDetails.password)
        jsonObject.put("civil_id", userDetails.civil_id)
        jsonObject.put("fcm_token", userDetails.fcm_token)

        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = REGISTER_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getBoolean("success")) {
                        userRegistrationResponse(
                            Gson().fromJson(
                                response.toString(),
                                UserRegistrationResponse::class.java
                            )
                        )
                        state.value = response.getString("success")
                    } else {
                        state.value = response.getString("success")
                    }
                },
                errorListener = { error ->
                    state.value = "false"
                    Log.d(
                        "CheckResponse", "error status = $error"
                    )
                }
            )
        } catch (e: Exception) {
            Log.d(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }

        return state
    }


    override suspend fun validateUserRequest(
        loginCredentials: LoginCredentials,
        userLoginResponseBody: (UserLoginResponse) -> Unit
    ): MutableStateFlow<String?> {
        val state = MutableStateFlow<String?>(null)

        val jsonObject = JSONObject()
        jsonObject.put("email", loginCredentials.email)
        jsonObject.put("fcm_token", loginCredentials.fcm_token)
        jsonObject.put("password", loginCredentials.password)

        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = ApiConstants.LOGIN_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getBoolean("success")) {
                        userLoginResponseBody(
                            Gson().fromJson(response.toString(), UserLoginResponse::class.java)
                        )
                        state.value = response.getString("success")
                    } else {
                        state.value = response.getString("success")
                    }
                },
                errorListener = { error ->
                    state.value = "false"
                    Log.d(
                        "CheckResponse", "error status = $error"
                    )
                }
            )
        } catch (e: Exception) {
            Log.d(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }

        return state
    }
}
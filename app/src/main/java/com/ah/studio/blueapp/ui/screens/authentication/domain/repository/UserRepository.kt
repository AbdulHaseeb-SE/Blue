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
import org.json.JSONObject
import java.nio.charset.Charset

class UserRepository(private val context: Context) : IUserRepository {
    override suspend fun registerUser(
        userDetails: User,
        userRegistrationResponse: (UserRegistrationResponse) -> Unit
    ) {
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
                    } else {
                        userRegistrationResponse(
                            UserRegistrationResponse(
                                message = response.getString("message"),
                                success = response.getBoolean("success"),
                                data = null
                            )
                        )
                    }
                },
                errorListener = { error ->
                    val responseCode = error.networkResponse?.statusCode ?: -1
                    val responseBody =
                        error.networkResponse?.data?.toString(Charset.defaultCharset()) ?: ""
                    Log.d(
                        "CheckResponse",
                        "error status code = $responseCode, response body = $responseBody"
                    )
                    try {
                        val errorJson = JSONObject(responseBody)
                        userRegistrationResponse(
                            UserRegistrationResponse(
                                message = errorJson.getString("message"),
                                success = errorJson.getBoolean("success"),
                                data = null
                            )
                        )
                        Log.d(
                            "CheckResponse",
                            "message = ${errorJson.getString("message")},\n" +
                                    "success = ${errorJson.getBoolean("success")}"
                        )
                    } catch (e: Exception) {
                        UserRegistrationResponse(
                            message = "Error: $responseCode $responseBody",
                            success = false,
                            data = null
                        )
                    }
                }
            )
        } catch (e: Exception) {
            Log.e(
                "CheckResponse",
                "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
    }


    override suspend fun validateUserRequest(
        loginCredentials: LoginCredentials,
        userLoginResponseBody: (UserLoginResponse) -> Unit
    ) {
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
                            Gson().fromJson(
                                response.toString(),
                                UserLoginResponse::class.java
                            )
                        )
                    } else {
                        userLoginResponseBody(
                            UserLoginResponse(
                                message = response.getString("message"),
                                success = response.getBoolean("success"),
                                data = null
                            )
                        )
                    }
                },
                errorListener = { error ->
                    val responseCode = error.networkResponse?.statusCode ?: -1
                    val responseBody =
                        error.networkResponse?.data?.toString(Charset.defaultCharset()) ?: ""
                    Log.d(
                        "CheckResponse",
                        "error status code = $responseCode, response body = $responseBody"
                    )
                    try {
                        val errorJson = JSONObject(responseBody)
                        userLoginResponseBody(
                            UserLoginResponse(
                                message = errorJson.getString("message"),
                                success = errorJson.getBoolean("success"),
                                data = null
                            )
                        )
                        Log.d(
                            "CheckResponse",
                            "message = ${errorJson.getString("message")},\n" +
                                    "success = ${errorJson.getBoolean("success")}"
                        )
                    } catch (e: Exception) {
                        UserLoginResponse(
                            message = "Error: $responseCode $responseBody",
                            success = false,
                            data = null
                        )
                    }
                }
            )
        } catch (e: Exception) {
            Log.e(
                "CheckResponse",
                "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
    }
}
package com.ah.studio.blueapp.ui.screens.account.domain.repository

import android.content.Context
import android.util.Log
import com.ah.studio.blueapp.api.VolleyInstance
import com.ah.studio.blueapp.ui.screens.account.domain.dto.LogoutResponse
import com.ah.studio.blueapp.ui.screens.account.domain.dto.changePassword.ChangePasswordBody
import com.ah.studio.blueapp.ui.screens.account.domain.dto.changePassword.ChangePasswordResponse
import com.ah.studio.blueapp.ui.screens.account.domain.dto.contactUs.ContactUsBody
import com.ah.studio.blueapp.ui.screens.account.domain.dto.contactUs.ContactUsResponse
import com.ah.studio.blueapp.ui.screens.account.domain.dto.refundPolicy.RefundPolicyResponse
import com.ah.studio.blueapp.ui.screens.account.domain.dto.termsAndConditions.TermsAndConditionsResponse
import com.ah.studio.blueapp.ui.screens.account.domain.dto.updateUser.UpdateUserDetailsResponse
import com.ah.studio.blueapp.ui.screens.account.domain.dto.updateUser.UserProfileDetails
import com.ah.studio.blueapp.ui.screens.account.domain.dto.userDetails.UserDetailsResponse
import com.ah.studio.blueapp.util.ApiConstants.CHANGE_PASSWORD_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.CONTACT_US_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.LOGOUT_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.REFUND_POLICY_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.TERMS_CONDITIONS_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.UPDATE_USER_ENDPOINT
import com.ah.studio.blueapp.util.ApiConstants.USER_DETAILS_ENDPOINT
import com.google.gson.Gson
import org.json.JSONObject
import java.nio.charset.Charset

class AccountRepository(private val context: Context) : IAccountRepository {

    override fun getTermsAndConditionResponse(termsAndConditionsResponse: (TermsAndConditionsResponse) -> Unit) {
        try {
            VolleyInstance(context).jsonObjectGetRequest(
                endPoint = TERMS_CONDITIONS_ENDPOINT,
                listener = { response ->
                    if (response.getString("status") == "200") {
                        termsAndConditionsResponse(
                            Gson().fromJson(
                                response.toString(),
                                TermsAndConditionsResponse::class.java
                            )
                        )
                    }
                },
                errorListener = { error ->
                    Log.d(
                        "CheckResponse", "error status = $error"
                    )
                }
            )
        } catch (e: Exception) {
            Log.e(
                "CheckResponse", "error status = ${e.message + e.localizedMessage + e.cause}"
            )
        }
    }

    override fun getRefundPolicyResponse(refundPolicyResponse: (RefundPolicyResponse) -> Unit) {
        try {
            VolleyInstance(context).jsonObjectGetRequest(
                endPoint = REFUND_POLICY_ENDPOINT,
                listener = { response ->
                    if (response.getInt("status") == 200) {
                        refundPolicyResponse(
                            Gson().fromJson(
                                response.toString(),
                                RefundPolicyResponse::class.java
                            )
                        )
                    } else {
                        refundPolicyResponse(
                            RefundPolicyResponse(
                                message = response.getString("message"),
                                status = response.getInt("status"),
                                data = null,
                                type = ""
                            )
                        )
                    }
                },
                errorListener = { error ->
                    val responseCode = error.networkResponse?.statusCode ?: -1
                    val responseBody =
                        error.networkResponse?.data?.toString(Charset.defaultCharset()) ?: ""
                    try {
                        val errorJson = JSONObject(responseBody)
                        refundPolicyResponse(
                            RefundPolicyResponse(
                                message = errorJson.getString("message"),
                                status = errorJson.getInt("status"),
                                data = null,
                                type = ""
                            )
                        )
                    } catch (e: Exception) {
                        RefundPolicyResponse(
                            message = "Error: $responseCode $responseBody",
                            status = 400,
                            data = null,
                            type = ""
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

    override fun getUserDetailsResponse(userDetailsResponse: (UserDetailsResponse) -> Unit) {
        try {
            VolleyInstance(context).jsonObjectGetRequest(
                endPoint = USER_DETAILS_ENDPOINT,
                listener = { response ->
                    if (response.getString("status") == "200") {
                        userDetailsResponse(
                            Gson().fromJson(
                                response.toString(),
                                UserDetailsResponse::class.java
                            )
                        )
                    }
                },
                errorListener = { error ->
                    Log.d(
                        "CheckResponse", "error status UserDetails = $error"
                    )
                }
            )
        } catch (e: Exception) {
            Log.e(
                "CheckResponse",
                "error status UserDetails = ${e.message + e.localizedMessage + e.cause}"
            )
        }
    }

    override fun getLogoutResponse(logoutResponse: (LogoutResponse) -> Unit) {
        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = LOGOUT_ENDPOINT,
                jsonObject = null,
                listener = { response ->
                    if (response.getInt("status") == 200) {
                        logoutResponse(
                            LogoutResponse(
                                message = response.getString("message"),
                                status = response.getInt("status"),
                                type = response.getString("type")
                            )
                        )
                    }
                }
            ) { error ->
                Log.d(
                    "CheckResponse", "error status Logout = $error"
                )
            }
        } catch (e: Exception) {
            Log.e(
                "CheckResponse",
                "error status Logout 2 = ${e.message + e.localizedMessage + e.cause}"
            )
        }
    }

    override fun getChangePasswordResponse(
        changePasswordBody: ChangePasswordBody,
        changePasswordResponse: (ChangePasswordResponse) -> Unit
    ) {
        val jsonObject = JSONObject()
        jsonObject.put("old_password", changePasswordBody.old_password)
        jsonObject.put("new_password", changePasswordBody.new_password)
        jsonObject.put("confirm_password", changePasswordBody.confirm_password)
        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = CHANGE_PASSWORD_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getBoolean("success")) {
                        changePasswordResponse(
                            ChangePasswordResponse(
                                message = response.getString("message"),
                                success = response.getBoolean("success")
                            )
                        )
                    } else {
                        changePasswordResponse(
                            ChangePasswordResponse(
                                message = response.getString("message"),
                                success = response.getBoolean("success")
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
                        changePasswordResponse(
                            ChangePasswordResponse(
                                message = errorJson.getString("message"),
                                success = errorJson.getBoolean("success")
                            )
                        )
                        Log.d(
                            "CheckResponse",
                            "message = ${errorJson.getString("message")},\n" +
                                    "success = ${errorJson.getBoolean("success")}"
                        )
                    } catch (e: Exception) {
                        ChangePasswordResponse(
                            message = "Error: $responseCode $responseBody",
                            success = false
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

    override fun getUpdateUserDetailsResponse(
        userProfileDetailsBody: UserProfileDetails,
        userProfileDetailsResponse: (UpdateUserDetailsResponse) -> Unit
    ) {
        val jsonObject = JSONObject()
        jsonObject.put("first_name", userProfileDetailsBody.first_name)
        jsonObject.put("last_name", userProfileDetailsBody.last_name)
        jsonObject.put("email", userProfileDetailsBody.email)
        jsonObject.put("phone_no", userProfileDetailsBody.phone_no)
        jsonObject.put("civil_id", userProfileDetailsBody.civil_id)
        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = UPDATE_USER_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getBoolean("success")) {
                        userProfileDetailsResponse(
                            Gson().fromJson(
                                response.toString(),
                                UpdateUserDetailsResponse::class.java
                            )
                        )
                    } else {
                        userProfileDetailsResponse(
                            UpdateUserDetailsResponse(
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
                    try {
                        val errorJson = JSONObject(responseBody)
                        userProfileDetailsResponse(
                            UpdateUserDetailsResponse(
                                message = errorJson.getString("message"),
                                success = errorJson.getBoolean("success"),
                                data = null
                            )
                        )
                    } catch (e: Exception) {
                        UpdateUserDetailsResponse(
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

    override fun getContactUsResponse(
        contactUsBody: ContactUsBody,
        contactUsResponse: (ContactUsResponse) -> Unit
    ) {
        val jsonObject = JSONObject()
        jsonObject.put("name", contactUsBody.name)
        jsonObject.put("answer", contactUsBody.answer)
        jsonObject.put("email", contactUsBody.email)

        try {
            VolleyInstance(context).jsonObjectPostRequest(
                endPoint = CONTACT_US_ENDPOINT,
                jsonObject = jsonObject,
                listener = { response ->
                    if (response.getInt("status") == 200) {
                        contactUsResponse(
                            Gson().fromJson(
                                response.toString(),
                                ContactUsResponse::class.java
                            )
                        )
                    } else {
                        contactUsResponse(
                            ContactUsResponse(
                                message = response.getString("message"),
                                status = response.getInt("status"),
                                data = null,
                                type = ""
                            )
                        )
                    }
                },
                errorListener = { error ->
                    val responseCode = error.networkResponse?.statusCode ?: -1
                    val responseBody =
                        error.networkResponse?.data?.toString(Charset.defaultCharset()) ?: ""
                    try {
                        val errorJson = JSONObject(responseBody)
                        contactUsResponse(
                            ContactUsResponse(
                                message = errorJson.getString("message"),
                                status = errorJson.getInt("status"),
                                data = null,
                                type = ""
                            )
                        )
                    } catch (e: Exception) {
                        ContactUsResponse(
                            message = "Error: $responseCode $responseBody",
                            status = 400,
                            data = null,
                            type = ""
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
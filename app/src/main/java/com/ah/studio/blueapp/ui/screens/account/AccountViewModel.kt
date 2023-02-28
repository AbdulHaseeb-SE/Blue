package com.ah.studio.blueapp.ui.screens.account

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import com.ah.studio.blueapp.ui.screens.account.domain.repository.IAccountRepository
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AccountViewModel(
    private val accountRepository: IAccountRepository
) : ViewModel(), IAccountViewModel {

    private val _termsAndCondition = MutableStateFlow<TermsAndConditionsResponse?>(null)
    override val termsAndCondition: Flow<TermsAndConditionsResponse?>
        get() = _termsAndCondition


    private val _refundPolicyResponse = MutableStateFlow<RefundPolicyResponse?>(null)
    override val refundPolicyResponse: Flow<RefundPolicyResponse?>
        get() = _refundPolicyResponse


    private val _userDetails = MutableStateFlow<UserDetailsResponse?>(null)
    override val userDetails: Flow<UserDetailsResponse?>
        get() = _userDetails


    private val _logoutResponse = MutableStateFlow<LogoutResponse?>(null)
    override val logoutResponse: Flow<LogoutResponse?>
        get() = _logoutResponse


    private val _changePasswordResponse = MutableStateFlow<ChangePasswordResponse?>(null)
    override val changePasswordResponse: Flow<ChangePasswordResponse?>
        get() = _changePasswordResponse


    private val _contactUsResponse = MutableStateFlow<ContactUsResponse?>(null)
    override val contactUsResponse: Flow<ContactUsResponse?>
        get() = _contactUsResponse


    private val _updateUserDetailsResponse = MutableStateFlow<UpdateUserDetailsResponse?>(null)
    override val updateUserDetailsResponse: Flow<UpdateUserDetailsResponse?>
        get() = _updateUserDetailsResponse

    override fun getTermsAndConditions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                accountRepository.getTermsAndConditionResponse { response ->
                    _termsAndCondition.value = response
                }
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

    override fun getRefundPolicyResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                accountRepository.getRefundPolicyResponse { response ->
                    _refundPolicyResponse.value = response
                }
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

    override fun getUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                accountRepository.getUserDetailsResponse { response ->
                    _userDetails.value = response
                }
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

    override fun getLogoutResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                accountRepository.getLogoutResponse { response ->
                    _logoutResponse.value = response
                }
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

    override fun getChangePasswordResponse(
        changePasswordBody: ChangePasswordBody
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                accountRepository.getChangePasswordResponse(changePasswordBody) { response ->
                    _changePasswordResponse.value = response
                }
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

    override fun getUpdateUserDetailsResponse(userProfileDetails: UserProfileDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                accountRepository.getUpdateUserDetailsResponse(userProfileDetails) { response ->
                    _updateUserDetailsResponse.value = response
                }
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

    override fun getContactUsResponse(contactUsBody: ContactUsBody) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                accountRepository.getContactUsResponse(contactUsBody) { response ->
                    _contactUsResponse.value = response
                }
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
}

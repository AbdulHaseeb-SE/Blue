package com.ah.studio.blueapp.ui.screens.account.domain.repository

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

interface IAccountRepository {
    fun getTermsAndConditionResponse(termsAndConditionsResponse: (TermsAndConditionsResponse) -> Unit)
    fun getRefundPolicyResponse(refundPolicyResponse: (RefundPolicyResponse) -> Unit)
    fun getUserDetailsResponse(userDetailsResponse: (UserDetailsResponse) -> Unit)
    fun getLogoutResponse(logoutResponse: (LogoutResponse) -> Unit)
    fun getChangePasswordResponse(
        changePasswordBody: ChangePasswordBody,
        changePasswordResponse: (ChangePasswordResponse) -> Unit
    )
    fun getUpdateUserDetailsResponse(
        userProfileDetailsBody: UserProfileDetails,
        userProfileDetailsResponse: (UpdateUserDetailsResponse)-> Unit
    )
    fun getContactUsResponse(
        contactUsBody: ContactUsBody,
        contactUsResponse: (ContactUsResponse) -> Unit
    )
}
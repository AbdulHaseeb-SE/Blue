package com.ah.studio.blueapp.ui.screens.account

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
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatDetails.BookedBoatDetailsResponse
import kotlinx.coroutines.flow.Flow

interface IAccountViewModel{
    fun getTermsAndConditions()
    fun getRefundPolicyResponse()
    fun getUserDetails()
    fun getLogoutResponse()
    fun getChangePasswordResponse(changePasswordBody: ChangePasswordBody)
    fun getUpdateUserDetailsResponse(userProfileDetails: UserProfileDetails)
    fun getContactUsResponse(contactUsBody: ContactUsBody)
    val updateUserDetailsResponse: Flow<UpdateUserDetailsResponse?>

    val termsAndCondition: Flow<TermsAndConditionsResponse?>
    val userDetails: Flow<UserDetailsResponse?>
    val logoutResponse: Flow<LogoutResponse?>
    val changePasswordResponse: Flow<ChangePasswordResponse?>
    val contactUsResponse: Flow<ContactUsResponse?>
    val refundPolicyResponse: Flow<RefundPolicyResponse?>

}
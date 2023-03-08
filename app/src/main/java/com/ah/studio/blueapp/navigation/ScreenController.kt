package com.ah.studio.blueapp.navigation

import com.ah.studio.blueapp.util.Constants.ACCOUNT_ROUTE
import com.ah.studio.blueapp.util.Constants.ADD_SEAFARER_ROUTE
import com.ah.studio.blueapp.util.Constants.BOAT_BOOKING_ROUTE
import com.ah.studio.blueapp.util.Constants.BOAT_DETAILS_ROUTE
import com.ah.studio.blueapp.util.Constants.BOOKED_BOAT_DETAIL_ROUTE
import com.ah.studio.blueapp.util.Constants.CAPTAIN_PAYMENT_ROUTE
import com.ah.studio.blueapp.util.Constants.CART_ROUTE
import com.ah.studio.blueapp.util.Constants.CATEGORY_DETAILS_ROUTE
import com.ah.studio.blueapp.util.Constants.CHANGE_PASSWORD_ROUTE
import com.ah.studio.blueapp.util.Constants.CONTACT_US_ROUTE
import com.ah.studio.blueapp.util.Constants.EDIT_PROFILE_ROUTE
import com.ah.studio.blueapp.util.Constants.GALLERY_ROUTE
import com.ah.studio.blueapp.util.Constants.HOME_ROUTE
import com.ah.studio.blueapp.util.Constants.MAIN_ROUTE
import com.ah.studio.blueapp.util.Constants.MY_BOOKING_ROUTE
import com.ah.studio.blueapp.util.Constants.MY_PARKING_ROUTE
import com.ah.studio.blueapp.util.Constants.NOTIFICATION_ROUTE
import com.ah.studio.blueapp.util.Constants.PACKAGE_ROUTE
import com.ah.studio.blueapp.util.Constants.PARKING_BOOKING_ROUTE
import com.ah.studio.blueapp.util.Constants.PARKING_MAP_ROUTE
import com.ah.studio.blueapp.util.Constants.PARKING_PAYMENT_ROUTE
import com.ah.studio.blueapp.util.Constants.PARK_BOAT_DETAILS_ROUTE
import com.ah.studio.blueapp.util.Constants.PARK_BOAT_ROUTE
import com.ah.studio.blueapp.util.Constants.PAYMENT_ROUTE
import com.ah.studio.blueapp.util.Constants.PRODUCT_DETAILS_ROUTE
import com.ah.studio.blueapp.util.Constants.PRODUCT_LIST_ROUTE
import com.ah.studio.blueapp.util.Constants.PRODUCT_ROUTE
import com.ah.studio.blueapp.util.Constants.REFUND_POLICY_ROUTE
import com.ah.studio.blueapp.util.Constants.SEAFARER_ROUTE
import com.ah.studio.blueapp.util.Constants.SELECT_DESTINATION_ROUTE
import com.ah.studio.blueapp.util.Constants.SHOW_REVIEWS_ROUTE
import com.ah.studio.blueapp.util.Constants.SIGN_IN_ROUTE
import com.ah.studio.blueapp.util.Constants.SIGN_UP_ROUTE
import com.ah.studio.blueapp.util.Constants.SPLASH_ROUTE
import com.ah.studio.blueapp.util.Constants.TERMS_CONDITIONS_ROUTE
import com.ah.studio.blueapp.util.Constants.THANK_YOU_ROUTE

sealed class ScreenController(val route: String) {
    object Splash : ScreenController(SPLASH_ROUTE)
    object Main : ScreenController(MAIN_ROUTE)
    object SignIn : ScreenController(SIGN_IN_ROUTE)
    object SignUp : ScreenController(SIGN_UP_ROUTE)
    object Home : ScreenController(HOME_ROUTE)
    object MyParking : ScreenController(MY_PARKING_ROUTE)
    object MyBooking : ScreenController(MY_BOOKING_ROUTE)
    object Seafarer : ScreenController(SEAFARER_ROUTE)
    object Account : ScreenController(ACCOUNT_ROUTE)
    object CategoryDetails : ScreenController(CATEGORY_DETAILS_ROUTE)
    object BoatDetails : ScreenController(BOAT_DETAILS_ROUTE)
    object Galley : ScreenController(GALLERY_ROUTE)
    object BoatBooking : ScreenController(BOAT_BOOKING_ROUTE)
    object SelectDestination : ScreenController(SELECT_DESTINATION_ROUTE)
    object Package : ScreenController(PACKAGE_ROUTE)
    object Product : ScreenController(PRODUCT_ROUTE)
    object ProductList : ScreenController(PRODUCT_LIST_ROUTE)
    object ProductDetails : ScreenController(PRODUCT_DETAILS_ROUTE)
    object Cart : ScreenController(CART_ROUTE)
    object Payment : ScreenController(PAYMENT_ROUTE)
    object TermsCondition : ScreenController(TERMS_CONDITIONS_ROUTE)
    object ThankYou : ScreenController(THANK_YOU_ROUTE)
    object ChangePassword : ScreenController(CHANGE_PASSWORD_ROUTE)
    object CaptainPayment : ScreenController(CAPTAIN_PAYMENT_ROUTE)
    object AddSeafarer : ScreenController(ADD_SEAFARER_ROUTE)
    object EditProfile : ScreenController(EDIT_PROFILE_ROUTE)
    object ContactUs : ScreenController(CONTACT_US_ROUTE)
    object RefundPolicy : ScreenController(REFUND_POLICY_ROUTE)
    object ParkBoat : ScreenController(PARK_BOAT_ROUTE)
    object ParkedBoatDetails : ScreenController(PARK_BOAT_DETAILS_ROUTE)
    object ParkingBooking : ScreenController(PARKING_BOOKING_ROUTE)
    object ParkingMap : ScreenController(PARKING_MAP_ROUTE)
    object ParkingPayment : ScreenController(PARKING_PAYMENT_ROUTE)
    object BookedBoatDetail : ScreenController(BOOKED_BOAT_DETAIL_ROUTE)
    object Notification : ScreenController(NOTIFICATION_ROUTE)
    object ShowReviews : ScreenController(SHOW_REVIEWS_ROUTE)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

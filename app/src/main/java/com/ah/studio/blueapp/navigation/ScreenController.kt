package com.ah.studio.blueapp.navigation

import com.ah.studio.blueapp.util.Constants.ACCOUNT_ROUTE
import com.ah.studio.blueapp.util.Constants.BOAT_BOOKING_ROUTE
import com.ah.studio.blueapp.util.Constants.BOAT_DETAILS_ROUTE
import com.ah.studio.blueapp.util.Constants.CATEGORY_DETAILS_ROUTE
import com.ah.studio.blueapp.util.Constants.GALLERY_ROUTE
import com.ah.studio.blueapp.util.Constants.HOME_ROUTE
import com.ah.studio.blueapp.util.Constants.MY_PARKING_ROUTE
import com.ah.studio.blueapp.util.Constants.SEAFARER_ROUTE
import com.ah.studio.blueapp.util.Constants.SELECT_DESTINATION_ROUTE
import com.ah.studio.blueapp.util.Constants.SIGN_IN_ROUTE
import com.ah.studio.blueapp.util.Constants.SIGN_UP_ROUTE
import com.ah.studio.blueapp.util.Constants.SPLASH_ROUTE

sealed class ScreenController(val route: String) {
    object Splash : ScreenController(SPLASH_ROUTE)
    object SignIn : ScreenController(SIGN_IN_ROUTE)
    object SignUp : ScreenController(SIGN_UP_ROUTE)
    object Home : ScreenController(HOME_ROUTE)
    object MyParking : ScreenController(MY_PARKING_ROUTE)
    object Seafarer : ScreenController(SEAFARER_ROUTE)
    object Account : ScreenController(ACCOUNT_ROUTE)
    object CategoryDetails : ScreenController(CATEGORY_DETAILS_ROUTE)
    object BoatDetails : ScreenController(BOAT_DETAILS_ROUTE)
    object Galley : ScreenController(GALLERY_ROUTE)
    object BoatBooking : ScreenController(BOAT_BOOKING_ROUTE)
    object SelectDestination : ScreenController(SELECT_DESTINATION_ROUTE)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

package com.ah.studio.blueapp.navigation

import com.ah.studio.blueapp.util.Constants.HOME_ROUTE
import com.ah.studio.blueapp.util.Constants.MAIN_SCREEN_ROUTE
import com.ah.studio.blueapp.util.Constants.SIGN_IN_ROUTE
import com.ah.studio.blueapp.util.Constants.SIGN_UP_ROUTE
import com.ah.studio.blueapp.util.Constants.SPLASH_ROUTE

sealed class ScreenController(val route: String) {
    object Splash : ScreenController(SPLASH_ROUTE)
    object SignIn : ScreenController(SIGN_IN_ROUTE)
    object SignUp : ScreenController(SIGN_UP_ROUTE)
    object MainScreen : ScreenController(MAIN_SCREEN_ROUTE)
    object Home : ScreenController(HOME_ROUTE)
    object MyParking : ScreenController(HOME_ROUTE)
    object Seafarer : ScreenController(HOME_ROUTE)
    object Account : ScreenController(HOME_ROUTE)
}

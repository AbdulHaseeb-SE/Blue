package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.authentication.SignInScreen

fun NavGraphBuilder.signInComposable(
    navHostController: NavHostController
) {
    composable(
        route = ScreenController.SignIn.route
    ) {
        SignInScreen(
            onLoginClick = {
                navHostController.navigate(ScreenController.Main.route)
            },
            onSignUpClick = {
                navHostController.navigate(ScreenController.SignUp.route)
            },
            onForgotClick = {

            }
        )
    }
}
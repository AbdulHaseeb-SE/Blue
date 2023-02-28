package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.authentication.SignUpScreen

fun NavGraphBuilder.signUpComposable(navHostController: NavHostController) {
    composable(
        route = ScreenController.SignUp.route
    ) {
        SignUpScreen(
            onRegisterClick = {
                navHostController.popBackStack()
                navHostController.navigate(ScreenController.SignIn.route)
            },
            onSignInClick = {
                navHostController.popBackStack()
                navHostController.navigate(ScreenController.SignIn.route)
            }
        )
    }
}
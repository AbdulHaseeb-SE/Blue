package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.authentication.SignInScreen
import com.ah.studio.blueapp.util.Graph

fun NavGraphBuilder.signInComposable(
    navHostController: NavHostController
) {
    composable(
        route = ScreenController.SignIn.route
    ) {
        SignInScreen(
            onLoginClick = {
                navHostController.navigate(Graph.Main)
            },
            onSignUpClick = {
                navHostController.navigate(ScreenController.SignUp.route)
            },
            onForgotClick = {

            }
        )
    }
}
package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.authentication.SignInScreen
import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse

fun NavGraphBuilder.signInComposable(
    navHostController: NavHostController
) {
    composable(
        route = ScreenController.SignIn.route
    ) {
        SignInScreen(
            navHostController = navHostController
        )
    }
}
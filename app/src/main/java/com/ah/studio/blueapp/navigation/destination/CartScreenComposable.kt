package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.CartScreen

fun NavGraphBuilder.cartScreenComposable(
    navHostController: NavHostController
) {
    composable(
        ScreenController.Cart.route
    ) {
        CartScreen(
            onProceedToCheckoutClick = {
                navHostController.navigate(ScreenController.Payment.route)
            },
            onBackButtonClick = {
                navHostController.popBackStack()
            }
        )
    }
}
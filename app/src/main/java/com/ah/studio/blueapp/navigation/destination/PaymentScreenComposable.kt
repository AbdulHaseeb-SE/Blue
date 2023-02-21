package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.PaymentScreen

fun NavGraphBuilder.paymentScreenComposable(navHostController: NavHostController) {
    composable(
        ScreenController.Payment.route
    ) {
        PaymentScreen(
            onPayAndConfirmClick = {
                navHostController.navigate(ScreenController.ThankYou.route)
            }
        )
    }
}
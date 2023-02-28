package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.seafarer.subScreens.CaptainPaymentScreen

fun NavGraphBuilder.captainPaymentScreenComposable(navHostController: NavHostController) {
    composable(
        ScreenController.CaptainPayment.route + "/{captainId}/{category}",
        arguments = listOf(
            navArgument(name = "captainId") {
                type = NavType.StringType
                nullable = false
            },
            navArgument(name = "category") {
                type = NavType.StringType
                nullable = false
            },
        )
    ) { entry ->
        entry.arguments?.getString("captainId")?.let { captainId ->
            entry.arguments?.getString("category")?.let { category ->
                CaptainPaymentScreen(
                    captainId = captainId.toInt(),
                    category = category,
                    onBackButtonClick = {
                        navHostController.popBackStack()
                    }
                )
            }
        }
    }
}
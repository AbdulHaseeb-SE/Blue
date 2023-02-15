package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.DestinationScreen

fun NavGraphBuilder.selectDestinationComposable(
    navHostController: NavHostController
) {
    composable(
        route = ScreenController.SelectDestination.route + "/{boatId}",
        arguments = listOf(
            navArgument(name = "boatId") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { entry ->
        entry.arguments?.getString("boatId")?.let { boatId ->
            DestinationScreen(
                boatId.toInt(),
                selectedDestinationId = {destinationID->
                    navHostController.popBackStack()
                    navHostController.navigate(ScreenController.BoatBooking.route + "/$boatId/$destinationID")
                }
            )
        }
    }
}
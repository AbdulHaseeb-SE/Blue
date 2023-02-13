package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.BoatBookingScreen

fun NavGraphBuilder.boatBookingComposable(
    navHostController: NavHostController
) {
    composable(
        route = ScreenController.BoatBooking.route + "/{boatId}/{destinationId}",
        arguments = listOf(
            navArgument(name = "boatId") {
                type = NavType.StringType
                nullable = false
            },
            navArgument(name = "destinationId") {
                type = NavType.StringType
                nullable = true
            }
        )
    ) { entry ->
        entry.arguments?.getString("boatId")?.let { boatId ->
            entry.arguments?.getString("destinationId")?.let { destinationId ->
                BoatBookingScreen(
                    boatId = boatId.toInt(),
                    destinationID = if (destinationId != "null") destinationId.toInt() else null,
                    selectDestinationClick = {
                        navHostController.navigate(ScreenController.SelectDestination.route + "/$boatId")
                    }
                )
            }
        }
    }
}
package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.BoatDetailsScreen

fun NavGraphBuilder.boatDetailsComposable(
    navHostController: NavHostController
) {
    composable(
        route = ScreenController.BoatDetails.route + "/{boatId}",
        arguments = listOf(
            navArgument(name = "boatId") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { entry ->
        entry.arguments?.getString("boatId")?.let { boatId ->
            BoatDetailsScreen(
                boatId.toInt(),
                onViewAllClick = {
                    navHostController.navigate(ScreenController.Galley.route + "/$boatId")
                },
                onBookNowClick = {
                    navHostController.navigate(ScreenController.BoatBooking.route + "/$boatId/${null}")
                },
                onReviewsClick = {
                    navHostController.navigate(ScreenController.ShowReviews.route)
                },
                onBackButtonClick = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}
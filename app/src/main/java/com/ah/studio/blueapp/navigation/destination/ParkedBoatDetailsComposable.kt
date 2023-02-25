package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.myParking.subScreens.ParkedBoatDetailScreen

fun NavGraphBuilder.parkedBoatDetailsComposable(navHostController: NavHostController) {
    composable(ScreenController.ParkedBoatDetails.route + "/{id}",
        arguments = listOf(
            navArgument(name = "id") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { entry ->
        entry.arguments?.getString("id")?.let { id ->
            ParkedBoatDetailScreen(
                id = id.toInt(),
                onBackButtonClick = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}
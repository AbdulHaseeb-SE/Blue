package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.GalleyScreen

fun NavGraphBuilder.galleryScreenComposable(
    navHostController: NavHostController
) {
    composable(
        route = ScreenController.Galley.route + "/{boatId}",
        arguments = listOf(
            navArgument(name = "boatId") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { entry ->
        entry.arguments?.getString("boatId")?.let { boatId ->
            GalleyScreen(
                boatId.toInt()
            )
        }
    }
}
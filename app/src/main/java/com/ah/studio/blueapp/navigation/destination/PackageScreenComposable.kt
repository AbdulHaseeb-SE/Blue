package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.PackageScreen

fun NavGraphBuilder.packageScreenComposable(
    navHostController: NavHostController
) {
    composable(
        route = ScreenController.Package.route + "/{boatId}",
        arguments = listOf(
            navArgument(name = "boatId") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { entry ->
        entry.arguments?.getString("boatId")?.let { boatId ->
            PackageScreen(
                boatId.toInt(),
                onNextClick = { navHostController.navigate(ScreenController.Product.route) },
                onSkipClick = { navHostController.navigate(ScreenController.Product.route) },
                onBackButtonClick = { navHostController.popBackStack() }
            )
        }
    }
}

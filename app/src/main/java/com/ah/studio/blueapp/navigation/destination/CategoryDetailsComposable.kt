package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.BoatCategoryDetailsScreen

fun NavGraphBuilder.categoryDetailsComposable(
    navHostController: NavHostController
) {
    composable(
        route = ScreenController.CategoryDetails.route,
        arguments = listOf(
            navArgument(name = "categoryName") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { entry ->
        entry.arguments?.getString("categoryName")?.let {
            BoatCategoryDetailsScreen(categoryName = it){
                navHostController.navigate(ScreenController.BoatDetails.route)
            }
        }
    }
}

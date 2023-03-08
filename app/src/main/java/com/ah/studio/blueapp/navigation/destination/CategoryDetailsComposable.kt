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
            },
            navArgument(name = "id") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { entry ->
        entry.arguments?.getString("categoryName")?.let { categoryName ->
            entry.arguments?.getString("id")?.let { id ->
                BoatCategoryDetailsScreen(
                    categoryName = categoryName,
                    categoryId = id.toInt(),
                    onBoatCardClick = {boatId->
                        navHostController.navigate(ScreenController.BoatDetails.route+"/$boatId")
                    },
                    onBackButtonClick = {
                        navHostController.popBackStack()
                    },
                    onNotificationButtonClick = {
                        navHostController.navigate(ScreenController.Notification.route)
                    }
                )
            }
        }
    }
}

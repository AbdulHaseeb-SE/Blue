package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.BoatCategoryDetailsScreen

fun NavGraphBuilder.categoryDetailsComposable(navHostController: NavHostController){
    composable(
        route = ScreenController.CategoryDetails.route + "/{CategoryName}",
        arguments = listOf(
            navArgument(name = "CategoryName"){
                type = NavType.StringType
                nullable = false
            }
        )
    ){entry->
        entry.arguments?.getString("CategoryName")
            ?.let { BoatCategoryDetailsScreen(categoryName = it) }
    }
}
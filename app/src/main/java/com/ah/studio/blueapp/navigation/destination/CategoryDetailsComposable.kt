package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.CategoryDetailsScreen
import com.ah.studio.blueapp.ui.screens.main.MainScreen
import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse
import com.ah.studio.blueapp.util.Graph

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
            ?.let { CategoryDetailsScreen(categoryName = it) }
    }
}
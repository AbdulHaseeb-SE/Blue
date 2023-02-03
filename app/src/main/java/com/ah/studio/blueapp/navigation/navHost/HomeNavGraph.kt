package com.ah.studio.blueapp.navigation.navHost

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.navigation.destination.*
import com.ah.studio.blueapp.util.Graph


@Composable
fun HomeNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        route = Graph.HomeGraph + "/{CategoryName}",
        startDestination = ScreenController.CategoryDetails.route,
    ) {
        this.argument("CategoryName") {
            type = NavType.StringType
            nullable = false
        }
        categoryDetailsComposable(navHostController)
    }
}
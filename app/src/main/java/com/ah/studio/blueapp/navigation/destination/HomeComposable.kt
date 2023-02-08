package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.HomeScreen
import com.ah.studio.blueapp.util.Graph

fun NavGraphBuilder.homeComposable(
    navHostController: NavHostController
) {
    composable(
        route = ScreenController.Home.route
    ) {
        HomeScreen(
            onClick = { categoryName ->
                navHostController.popBackStack()
                navHostController.navigate(Graph.HomeGraph + "/$categoryName")
            }
        )
    }
}
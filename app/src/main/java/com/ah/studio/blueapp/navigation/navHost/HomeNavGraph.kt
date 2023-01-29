package com.ah.studio.blueapp.navigation.navHost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.navigation.destination.mainScreenComposable
import com.ah.studio.blueapp.util.Graph

@Composable
fun HomeNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.Home,
        startDestination = ScreenController.MainScreen.route
    ) {
        mainScreenComposable(navHostController)
    }
}
package com.ah.studio.blueapp.navigation.navHost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.navigation.destination.*
import com.ah.studio.blueapp.util.Graph

@Composable
fun MainNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.Main,
        startDestination = ScreenController.Home.route
    ) {
        homeComposable(navHostController)
        categoryDetailsComposable(navHostController)
        myParkingComposable(navHostController)
        seafarerComposable(navHostController)
        accountComposable(navHostController)
    }
}
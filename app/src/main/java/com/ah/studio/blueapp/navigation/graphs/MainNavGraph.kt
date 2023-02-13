package com.ah.studio.blueapp.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.navigation.destination.accountComposable
import com.ah.studio.blueapp.navigation.destination.homeComposable
import com.ah.studio.blueapp.navigation.destination.myParkingComposable
import com.ah.studio.blueapp.navigation.destination.seafarerComposable
import com.ah.studio.blueapp.util.Graph

@Composable
fun MainNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.Main,
        startDestination = ScreenController.Home.route
    ) {

        homeComposable(navHostController)
        myParkingComposable(navHostController)
        seafarerComposable(navHostController)
        accountComposable(navHostController)
        homeNavGraph(navHostController)

    }
}
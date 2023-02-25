package com.ah.studio.blueapp.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.navigation.destination.mainScreenComposable
import com.ah.studio.blueapp.navigation.destination.splashComposable
import com.ah.studio.blueapp.util.Graph

@Composable
fun RootNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = ScreenController.Splash.route
    ) {
        splashComposable(navHostController)
        authNavGraph(navHostController)
        mainScreenComposable()
    }
}
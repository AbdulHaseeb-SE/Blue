package com.ah.studio.blueapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ah.studio.blueapp.navigation.destination.signUpComposable
import com.ah.studio.blueapp.util.Graph

@Composable
fun RootNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = ScreenController.SignUp.route
    ) {
        signUpComposable(navHostController)

    }
}
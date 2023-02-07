package com.ah.studio.blueapp.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.navigation.destination.signInComposable
import com.ah.studio.blueapp.navigation.destination.signUpComposable
import com.ah.studio.blueapp.util.Graph

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = ScreenController.SignUp.route
    ) {
        signUpComposable(navHostController)
        signInComposable(navHostController)
    }
}
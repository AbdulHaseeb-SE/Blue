package com.ah.studio.blueapp.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.navigation.destination.boatDetailsComposable
import com.ah.studio.blueapp.navigation.destination.categoryDetailsComposable
import com.ah.studio.blueapp.util.Graph

fun NavGraphBuilder.homeNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.HomeGraph + "/{categoryName}",
        startDestination = ScreenController.CategoryDetails.route
    ) {
        categoryDetailsComposable(navHostController)
        boatDetailsComposable(navHostController)
    }
}

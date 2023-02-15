package com.ah.studio.blueapp.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.navigation.destination.*
import com.ah.studio.blueapp.util.Graph

fun NavGraphBuilder.homeNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.HomeGraph + "/{categoryName}/{id}",
        startDestination = ScreenController.CategoryDetails.route
    ) {
        categoryDetailsComposable(navHostController)
        boatDetailsComposable(navHostController)
        galleryScreenComposable(navHostController)
        boatBookingComposable(navHostController)
        selectDestinationComposable(navHostController)
        packageScreenComposable(navHostController)
        productScreenComposable(navHostController)
        productListComposable(navHostController)
        productDetailsComposable(navHostController)
    }
}

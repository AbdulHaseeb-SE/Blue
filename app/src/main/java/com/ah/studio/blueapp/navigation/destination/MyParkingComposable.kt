package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.myParking.MyParkingScreen
import com.ah.studio.blueapp.util.Graph

fun NavGraphBuilder.myParkingComposable(navHostController: NavHostController) {
    composable(
        route = ScreenController.MyParking.route
    ) {
        MyParkingScreen(
            onAddBoatClick = {
                navHostController.navigate(Graph.MyParkingGraph)
            },
            onParkedBoatClick = {id->
                navHostController.navigate(ScreenController.ParkedBoatDetails.route+"/$id")
            }
        )
    }
}
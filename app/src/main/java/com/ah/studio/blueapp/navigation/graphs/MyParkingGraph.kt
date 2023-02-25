package com.ah.studio.blueapp.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.navigation.destination.bookingComposable
import com.ah.studio.blueapp.navigation.destination.parkBoatComposable
import com.ah.studio.blueapp.navigation.destination.parkingMapComposable
import com.ah.studio.blueapp.navigation.destination.parkingPaymentComposable
import com.ah.studio.blueapp.util.Graph

fun NavGraphBuilder.myParkingNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.MyParkingGraph,
        startDestination = ScreenController.ParkBoat.route
    ) {
        parkBoatComposable(navHostController)
        bookingComposable(navHostController)
        parkingMapComposable(navHostController)
        parkingPaymentComposable(navHostController)
    }
}
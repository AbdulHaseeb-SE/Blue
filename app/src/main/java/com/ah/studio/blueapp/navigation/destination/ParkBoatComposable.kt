package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.myParking.subScreens.BoatListScreen

fun NavGraphBuilder.parkBoatComposable(navHostController: NavHostController) {
    composable(ScreenController.ParkBoat.route) {
        BoatListScreen(
            onParkNowClick = { id ->
                navHostController.navigate(ScreenController.ParkingBooking.route + "/$id")
            },
            onBackButtonClick = {
                navHostController.popBackStack()
            }
        )
    }
}
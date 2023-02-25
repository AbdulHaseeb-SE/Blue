package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.myParking.subScreens.BookingScreen

fun NavGraphBuilder.bookingComposable(navHostController: NavHostController) {
    composable(ScreenController.ParkingBooking.route + "/{id}",
        arguments = listOf(
            navArgument(name = "id") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { entry ->
        entry.arguments?.getString("id")?.let { id ->
            BookingScreen(
                id = id.toInt(),
                onBackButtonCLick = {
                    navHostController.popBackStack()
                },
                onContinueButtonClick = {selectedDate, startingTime, endingTime, price->
                    navHostController.navigate(ScreenController.ParkingMap.route+"/$id/$selectedDate/$startingTime/$endingTime/$price")
                }
            )
        }
    }
}
package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.myParking.subScreens.MapScreen

fun NavGraphBuilder.parkingMapComposable(navHostController: NavHostController) {
    composable(ScreenController.ParkingMap.route + "/{id}/{selectedDate}/{startingTime}/{endingTime}/{price}",
        arguments = listOf(
            navArgument(name = "id") {
                type = NavType.StringType
                nullable = false
            },
            navArgument(name = "selectedDate") {
                type = NavType.StringType
                nullable = false
            },
            navArgument(name = "startingTime") {
                type = NavType.StringType
                nullable = false
            },
            navArgument(name = "endingTime") {
                type = NavType.StringType
                nullable = false
            },
            navArgument(name = "price") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { entry ->
        entry.arguments?.getString("id")?.let { id ->
            entry.arguments?.getString("selectedDate")?.let { selectedDate ->
                entry.arguments?.getString("startingTime")?.let { startingTime ->
                    entry.arguments?.getString("endingTime")?.let { endingTime ->
                        entry.arguments?.getString("price")?.let { price ->
                            MapScreen(
                                id.toInt(),
                                selectedDate = selectedDate,
                                startingTime = startingTime,
                                endingTime = endingTime,
                                price = price.toDouble(),
                                onBackButtonClick = {
                                    navHostController.popBackStack()
                                },
                                onNextButtonClick = {_, selectedDate, startingTime, endingTime, price->
                                    navHostController.navigate(ScreenController.ParkingPayment.route + "/$id/$selectedDate/$startingTime/$endingTime/$price")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.myParking.MyParkingScreen

fun NavGraphBuilder.myParkingComposable(navHostController: NavHostController){
    composable(
        route = ScreenController.MyParking.route
    ){
        MyParkingScreen(navHostController = navHostController)
    }
}
package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.myBooking.MyBookingScreen

fun NavGraphBuilder.myBookingComposable(navHostController: NavHostController){
    composable(ScreenController.MyBooking.route){
        MyBookingScreen()
    }
}
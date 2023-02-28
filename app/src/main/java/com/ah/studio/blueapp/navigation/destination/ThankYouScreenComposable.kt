package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.ThankYouBookingScreen
import com.ah.studio.blueapp.util.Graph

fun NavGraphBuilder.thankYouScreenComposable(
    navHostController: NavHostController
){
    composable(
        ScreenController.ThankYou.route
    ){
        ThankYouBookingScreen(
            onHomeButtonClick = {
                navHostController.navigate(Graph.Main)
            }
        )
    }
}
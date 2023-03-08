package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.NotificationScreen

fun NavGraphBuilder.notificationComposable(navHostController: NavHostController){
    composable(ScreenController.Notification.route){
        NotificationScreen(
            onBackButtonClick = {
                navHostController.popBackStack()
            }
        )
    }
}
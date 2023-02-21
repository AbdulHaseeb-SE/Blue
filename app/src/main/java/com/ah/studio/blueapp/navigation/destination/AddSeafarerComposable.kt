package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.seafarer.subScreens.AddNewCaptainScreen

fun NavGraphBuilder.addSeafarerComposable(navHostController: NavHostController) {
    composable(ScreenController.AddSeafarer.route) {
        AddNewCaptainScreen()
    }
}
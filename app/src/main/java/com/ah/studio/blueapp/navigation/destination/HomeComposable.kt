package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.authentication.SignInScreen
import com.ah.studio.blueapp.ui.screens.home.HomeScreen

fun NavGraphBuilder.homeComposable(navHostController: NavHostController){
    composable(
        route = ScreenController.Home.route
    ){
        HomeScreen(navHostController = navHostController)
    }
}
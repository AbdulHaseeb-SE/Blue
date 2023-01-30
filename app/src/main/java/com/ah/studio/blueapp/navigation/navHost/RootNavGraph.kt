package com.ah.studio.blueapp.navigation.navHost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.navigation.destination.mainScreenComposable
import com.ah.studio.blueapp.navigation.destination.signInComposable
import com.ah.studio.blueapp.navigation.destination.signUpComposable
import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse
import com.ah.studio.blueapp.util.Graph

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    bottomNavItemList: MutableList<BottomNavItemResponse>
) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = ScreenController.SignUp.route
    ) {
        signUpComposable(navHostController)
        signInComposable(navHostController)
        mainScreenComposable(bottomNavItemList)
    }
}
package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.ui.screens.main.MainScreen
import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse
import com.ah.studio.blueapp.util.Graph


fun NavGraphBuilder.mainScreenComposable(
    bottomNavItemList: MutableList<BottomNavItemResponse>
){
    composable(
        route = Graph.Main
    ){
        MainScreen(bottomNavItemList = bottomNavItemList)
    }
}
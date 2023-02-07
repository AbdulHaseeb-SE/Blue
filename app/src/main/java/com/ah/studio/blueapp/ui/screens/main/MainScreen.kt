package com.ah.studio.blueapp.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ah.studio.blueapp.navigation.graphs.MainNavGraph
import com.ah.studio.blueapp.ui.component.BottomNavBar
import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse
import com.ah.studio.blueapp.ui.theme.SeaBlue50

@Composable
fun MainScreen(
    navHostController: NavHostController = rememberNavController(),
    bottomNavItemList: MutableList<BottomNavItemResponse> = mutableListOf()
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navHostController,
                bottomNavItemList
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            MainNavGraph(navHostController = navHostController)
        }
    }
}

@Composable
fun BottomNavigationBar(
    navHostController: NavHostController,
    bottomNavItemList: MutableList<BottomNavItemResponse>
) {
    Card(
        elevation = 14.dp,
        backgroundColor = SeaBlue50
    ) {
        BottomNavBar(
            items = bottomNavItemList, navController = navHostController
        ) { currentItem ->
            navHostController.navigate(currentItem.route) {
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    }
}
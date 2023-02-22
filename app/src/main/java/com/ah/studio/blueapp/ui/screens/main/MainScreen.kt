package com.ah.studio.blueapp.ui.screens.main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ah.studio.blueapp.SessionManager
import com.ah.studio.blueapp.navigation.graphs.MainNavGraph
import com.ah.studio.blueapp.ui.component.BottomNavBar
import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse
import com.ah.studio.blueapp.ui.screens.main.viewModel.BottomNavViewModel
import com.ah.studio.blueapp.ui.theme.SeaBlue50
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@Composable
fun MainScreen(
    navHostController: NavHostController = rememberNavController()
) {
    val sessionManager = SessionManager(LocalContext.current)
    Log.d("CheckRole", "In Main Screen role ="+sessionManager.getRole().toString())


    var bottomNavItemList: List<BottomNavItemResponse> by remember {
        mutableStateOf(emptyList())
    }
    val viewModel: BottomNavViewModel = getKoin().get()

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.bottomNavItems().collectLatest {
                bottomNavItemList = it
            }
        }
    }


    Scaffold(
        bottomBar = {
            if (bottomNavItemList.isNotEmpty()){
                BottomNavigationBar(
                    navHostController,
                    bottomNavItemList as MutableList<BottomNavItemResponse>
                )
            }
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
package com.ah.studio.blueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ah.studio.blueapp.navigation.navHost.RootNavGraph
import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse
import com.ah.studio.blueapp.ui.screens.main.viewModel.BottomNavViewModel
import com.ah.studio.blueapp.ui.theme.BlueAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {


    lateinit var navHostController: NavHostController
    private val bottomNavItemList: MutableList<BottomNavItemResponse> = mutableListOf()
    private val viewModel: BottomNavViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueAppTheme {
                navHostController = rememberNavController()
                ObserverBottomNavItems(
                    viewModel,
                    bottomNavItemList = bottomNavItemList
                )
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RootNavGraph(
                        navHostController = navHostController,
                        bottomNavItemList = bottomNavItemList
                    )
                }
            }
        }
    }

    @Composable
    fun ObserverBottomNavItems(
        viewModel: BottomNavViewModel, bottomNavItemList: MutableList<BottomNavItemResponse>
    ) {
        LaunchedEffect(key1 = true) {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.bottomNavItems().collect {
                    bottomNavItemList += it
                }
            }
        }
    }
}
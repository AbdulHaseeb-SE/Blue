package com.ah.studio.blueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ah.studio.blueapp.navigation.graphs.RootNavGraph
import com.ah.studio.blueapp.ui.theme.BlueAppTheme
import com.ah.studio.blueapp.ui.theme.fontFamily

class MainActivity : ComponentActivity() {
    lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            fontFamily
            BlueAppTheme {
                navHostController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RootNavGraph(
                        navHostController = navHostController
                    )
                }
            }
        }
    }
}
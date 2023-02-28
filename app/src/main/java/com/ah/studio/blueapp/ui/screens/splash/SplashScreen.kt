package com.ah.studio.blueapp.ui.screens.splash

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.SessionManager
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.theme.OxfordBlue900
import com.ah.studio.blueapp.util.Graph
import com.ah.studio.blueapp.util.SetStatusBarColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val token = SessionManager(LocalContext.current).getToken()

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    
    SetStatusBarColor(color = OxfordBlue900)

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(4000)
        navController.popBackStack()
        if (token == "") {
            navController.navigate(Graph.AUTHENTICATION)
        } else {
            navController.navigate(ScreenController.Main.route)
        }
    }
    Splash(alpha = alphaAnim.value)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Splash(alpha: Float) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(OxfordBlue900)
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(0.dp)
                .background(OxfordBlue900)
                .alpha(alpha = alpha),
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = "Splash Screen"
        )
    }
}

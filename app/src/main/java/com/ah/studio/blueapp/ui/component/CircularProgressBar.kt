package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.ui.theme.SeaBlue400

@Composable
fun CircularProgressBar(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center),
            color = SeaBlue400,
            strokeWidth = 4.dp
        )
    }
}
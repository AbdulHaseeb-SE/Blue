package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun HorizontalGradientLine(
    width: Dp,
    height: Dp,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .background(
                brush = Brush.horizontalGradient(
                    colors = colors,
                    startX = 5f
                )
            )
    )
}

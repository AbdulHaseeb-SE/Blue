package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
fun RoundedCornerLine(
    width: Dp,
    height: Dp,
    color: Color,
    shape: Shape,
    elevation: Dp,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(width)
            .height(height)
            .background(Color.Transparent),
        shape = shape,
        elevation = elevation
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color)
        ) {}
    }
}
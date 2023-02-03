package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.ui.theme.SeaBlue08Percent
import com.ah.studio.blueapp.ui.theme.SeaBlue400
import com.ah.studio.blueapp.ui.theme.Shapes

@Composable
fun BlueRoundedCornerShape(
    modifier: Modifier = Modifier,
    borderColor: Color = SeaBlue400,
    containerColor: Color = SeaBlue08Percent,
    shape: Shape = Shapes.medium,
    content: @Composable (ColumnScope) -> Unit
) {
    Card(
        modifier = modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape
            ),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        shape = shape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        )
    ) {
        content(this)
    }
}
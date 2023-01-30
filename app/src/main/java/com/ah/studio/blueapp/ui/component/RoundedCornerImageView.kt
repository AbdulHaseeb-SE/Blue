package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.Shapes

@Composable
fun RoundedCornerImageView(
    painter: Painter,
    shape: Shape,
    contentScale: ContentScale,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .background(Color.Transparent),
        shape = shape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Image(
            painter = painter,
            contentDescription = stringResource(R.string.boat_image),
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize()
        )
    }
}

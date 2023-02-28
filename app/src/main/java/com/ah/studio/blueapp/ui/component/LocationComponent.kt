package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.ui.theme.fontFamily


@Composable
fun LocationComponent(
    painter: Painter,
    locationText: String,
    locationStartPadding: Dp,
    rowTopPadding: Dp,
    iconPaddingStart: Dp,
    modifier: Modifier = Modifier,
    locationTextFontWeight: FontWeight = FontWeight(600)
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = rowTopPadding),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = modifier
                .padding(start = iconPaddingStart)
        )
        Text(
            text = locationText,
            fontSize = 17.sp,
            fontWeight = locationTextFontWeight,
            fontFamily = fontFamily,
            color = Color.Black,
            modifier = modifier.padding(start = locationStartPadding)
        )
    }
}
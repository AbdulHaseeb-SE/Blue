package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.ui.theme.SeaBlue400
import com.ah.studio.blueapp.ui.theme.fontFamily

@Composable
fun Button(
    width: Dp,
    height: Dp,
    text: String,
    backgroundColor: Color,
    shape: Shape,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal,
    onButtonClick: () -> Unit
) {
    Button(
        onClick = {
            onButtonClick()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = backgroundColor
        ),
        shape = shape,
        modifier = modifier
            .width(width)
            .height(height)
    ) {
        Text(
            text = text,
            fontSize = 17.sp,
            color = Color.Black,
            fontFamily = fontFamily,
            fontWeight = fontWeight
        )
    }
}
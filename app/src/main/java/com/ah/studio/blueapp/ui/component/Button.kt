package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.ui.theme.PaddingHalf
import com.ah.studio.blueapp.ui.theme.fontFamily

@Composable
fun Button(
    width: Dp,
    height: Dp,
    text: String,
    backgroundColor: Color,
    shape: Shape,
    modifier: Modifier = Modifier,
    buttonIcon: Painter? = null,
    fontWeight: FontWeight = FontWeight.Normal,
    textColor: Color = Color.Black,
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
        if (buttonIcon != null) {
            Image(
                painter = buttonIcon,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = PaddingHalf)
            )
        }
        Text(
            text = text,
            fontSize = 17.sp,
            color = textColor,
            fontFamily = fontFamily,
            fontWeight = fontWeight
        )
    }
}
package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.ui.theme.OxfordBlue900
import com.ah.studio.blueapp.ui.theme.Shapes
import com.ah.studio.blueapp.ui.theme.fontFamily

@Composable
fun ProductItem(
    itemName: String,
    itemDescription: String,
    itemImage: Painter,
    price: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        RoundedCornerImageView(
            painter = itemImage,
            shape = Shapes.medium,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(0.35f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = itemName,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Color.Black,
                maxLines = 1
            )

            Text(
                text = itemDescription,
                fontSize = 17.sp,
                fontWeight = FontWeight.Light,
                fontFamily = fontFamily,
                color = Color.Black,
                maxLines = 2,
                modifier = Modifier.padding(start = 2.dp)
            )

            Text(
                text = price,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = OxfordBlue900,
                maxLines = 1,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }
    }
}
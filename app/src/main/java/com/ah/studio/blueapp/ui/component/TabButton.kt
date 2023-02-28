package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.SeaBlue400
import com.ah.studio.blueapp.ui.theme.fontFamily

@Composable
fun TabButton(
    tabIcon: Painter,
    tabText: String,
    trailingIcon: Painter,
    modifier: Modifier = Modifier
) {
    BlueRoundedCornerShape(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 14.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.7f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = tabIcon,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 11.dp)
                        .size(30.dp)
                )

                Divider(
                    thickness = 1.dp,
                    color = SeaBlue400,
                    modifier = Modifier
                        .width(1.dp)
                        .height(25.dp)
                )

                Text(
                    text = tabText,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fontFamily,
                    modifier = Modifier.padding(start = 13.dp)
                )
            }

            Image(
                painter = trailingIcon,
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
fun PreviewTabButton() {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_calendar_filled),
        tabText = "My Bookings",
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right)
    )
}
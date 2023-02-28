package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.PaddingHalf
import com.ah.studio.blueapp.ui.theme.SeaBlue08Percent
import com.ah.studio.blueapp.ui.theme.fontFamily


@Composable
fun DestinationListComponent(
    destinationName: String,
    destinationTime: String,
    cost: String,
    containerColor: Color = SeaBlue08Percent,
    textColor: Color,
    costTextColor: Color,
    onClick: () -> Unit
) {
    BlueRoundedCornerShape(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = PaddingHalf)
            .clickable { onClick() },
        containerColor = containerColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = destinationName,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    color = textColor,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    modifier = Modifier
                        .width(170.dp)
                        .padding(top = 13.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_clock_outlined),
                        contentDescription = stringResource(R.string.clock_icon),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp),
                        colorFilter = ColorFilter.tint(textColor)
                    )
                    Text(
                        text = destinationTime,
                        fontSize = 14.sp,
                        fontFamily = fontFamily,
                        color = textColor,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                    )
                }
            }

            Text(
                text = cost,
                fontSize = 20.sp,
                fontFamily = fontFamily,
                color = costTextColor,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
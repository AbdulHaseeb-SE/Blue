package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.*

@Composable
fun BoatListCard(
    boatImage: Painter,
    boatName: String,
    boatLocation: String,
    boatPrice: String,
    modifier: Modifier = Modifier,
    titleFontWeight: FontWeight = FontWeight.SemiBold,
    locationFontWeight: FontWeight = FontWeight(600),
    priceFontWeight: FontWeight = FontWeight.Normal,
    paddingBetweenText: Dp = 11.dp,
    isParked: Boolean = false,
    viewOnMapText: String = "",
    parkingStatusText: String = "",
    onParkNowClick: () -> Unit
) {
    Row(
        modifier = modifier
    ) {
        RoundedCornerImageView(
            painter = boatImage,
            shape = Shapes.medium,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .background(White50Percent)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = boatName,
                fontSize = 17.sp,
                fontWeight = titleFontWeight,
                fontFamily = fontFamily,
                color = Color.Black,
                modifier = Modifier.padding(bottom = paddingBetweenText)
            )

            LocationComponent(
                painterResource(id = R.drawable.ic_location_grey),
                locationText = boatLocation,
                locationStartPadding = 6.dp,
                rowTopPadding = 0.dp,
                iconPaddingStart = 0.dp,
                modifier = Modifier
                    .padding(0.dp)
                    .height(20.dp),
                locationTextFontWeight = locationFontWeight
            )

            Text(
                text = boatPrice,
                fontSize = 17.sp,
                fontWeight = priceFontWeight,
                fontFamily = fontFamily,
                color = OxfordBlue900,
                modifier = Modifier.padding(top = paddingBetweenText)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = viewOnMapText,
                    fontSize = 17.sp,
                    fontWeight = titleFontWeight,
                    fontFamily = fontFamily,
                    color = OxfordBlue900,
                    modifier = Modifier.padding(
                        top = 4.dp,
                        end = PaddingLarge
                    )
                )

                Text(
                    text = parkingStatusText,
                    fontSize = 17.sp,
                    fontWeight = titleFontWeight,
                    fontFamily = fontFamily,
                    color = if (isParked) Color.Black else SeaBlue400,
                    style = TextStyle(
                        textDecoration = if (!isParked) TextDecoration.Underline else TextDecoration.None
                    ),
                    modifier = Modifier
                        .padding(top = (4.dp))
                        .clickable {
                            if (!isParked) {
                                onParkNowClick()
                            }
                        }
                )
            }
        }
    }
}

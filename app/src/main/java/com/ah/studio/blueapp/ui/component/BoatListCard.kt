package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.screens.home.LocationComponent
import com.ah.studio.blueapp.ui.theme.*

@Composable
fun BoatListCard(
    boatImage: Painter,
    boatName: String,
    boatLocation: String,
    boatPrice: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        RoundedCornerImageView(
            painter = boatImage,
            shape = Shapes.medium,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(0.35f)
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
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            LocationComponent(
                painterResource(id = R.drawable.ic_location_grey),
                locationText = boatLocation,
                locationStartPadding = 6.dp,
                rowTopPadding = 0.dp,
                iconPaddingStart = 0.dp,
                modifier = Modifier
                    .padding(0.dp)
                    .height(20.dp)
            )

            Text(
                text = boatPrice,
                fontSize = 17.sp,
                fontFamily = fontFamily,
                color = OxfordBlue900,
                modifier = Modifier.padding(top = 12.dp)
            )

        }
    }
}

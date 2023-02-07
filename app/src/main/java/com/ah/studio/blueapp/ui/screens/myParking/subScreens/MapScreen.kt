package com.ah.studio.blueapp.ui.screens.myParking.subScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = null,
                text = "",
                navigationIconContentDescription = "",
                actionIcons = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = stringResource(
                            R.string.button_notification
                        ),
                        modifier = Modifier
                            .padding(end = PaddingDouble)
                            .size(24.dp)
                    )
                },
                onNavigationIconClick = { /*TODO*/ })
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
//            Map()
            BottomCardSection()
        }
    }
}

@Composable
fun BottomCardSection() {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        androidx.compose.material3.Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(207.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(
                topStart = 30.dp,
                topEnd = 30.dp,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            )
        ) {
            Card(
                boatImage = painterResource(id = R.drawable.ic_boat),
                parkingSlotName = "D1 Slot AL Kandari Parking ",
                boatLocation = stringResource(id = R.string.al_jahra_kuwait),
                boatPrice = "4.5 KWD / DAY",
                rating = 4.9f,
                priceFontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(
                        top = 24.dp,
                        start = PaddingDouble,
                        bottom = PaddingDouble
                    ),
                paddingBetweenText = 14.dp
            )
        }
    }
}

@Composable
fun Card(
    boatImage: Painter,
    parkingSlotName: String,
    boatLocation: String,
    boatPrice: String,
    rating: Float,
    modifier: Modifier = Modifier,
    titleFontWeight: FontWeight = FontWeight.SemiBold,
    locationFontWeight: FontWeight = FontWeight(600),
    priceFontWeight: FontWeight = FontWeight.Normal,
    paddingBetweenText: Dp = 11.dp
) {
    Row(
        modifier = modifier.background(Color.White)
    ) {
        RoundedCornerImageView(
            painter = boatImage,
            shape = Shapes.medium,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(0.35f),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = parkingSlotName,
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
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
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star_outlined),
                        contentDescription = stringResource(R.string.rating_star),
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )
                    Text(
                        text = rating.toString(),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fontFamily,
                        color = OxfordBlue900,
                        modifier = Modifier.padding(start = 7.dp)
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewMapScreen() {
    MapScreen()
}

package com.ah.studio.blueapp.ui.screens.myParking.subScreens

import android.annotation.SuppressLint
import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BoatListCard
import com.ah.studio.blueapp.ui.component.Button
import com.ah.studio.blueapp.ui.component.LocationComponent
import com.ah.studio.blueapp.ui.component.RoundedCornerImageView
import com.ah.studio.blueapp.ui.screens.home.domain.dto.Boat
import com.ah.studio.blueapp.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoatListScreen() {

    val boatList: MutableList<Boat> = mutableListOf(
        Boat(
            boatImage = painterResource(id = R.drawable.ic_boat),
            boatName = "Catamaran Boats",
            location = stringResource(id = R.string.al_jahra_kuwait),
            price = "Starting from 100.000 KWD",
            parkingStatus = true
        ),
        Boat(
            boatImage = painterResource(id = R.drawable.boat),
            boatName = "Catamaran Boats",
            location = stringResource(id = R.string.al_jahra_kuwait),
            price = "4.5 KWD / Day",
            parkingStatus = false
        )
    )


    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 22.dp, start = PaddingDouble, end = PaddingDouble)
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LocationComponent(
                painter = painterResource(id = R.drawable.ic_location),
                locationText = stringResource(R.string.al_jahra_kuwait),
                locationStartPadding = PaddingHalf,
                rowTopPadding = 0.dp,
                iconPaddingStart = 0.dp
            )

            FirsImageRowSection()

            AvailableCareersSection(boatList)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Transparent)
                    .weight(5f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(
                    width = 0.dp,
                    height = 50.dp,
                    text = stringResource(R.string.open_maps),
                    backgroundColor = SeaBlue400,
                    shape = Shapes.medium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 47.dp,
                            bottom = 47.dp,
                            start = 46.dp,
                            end = 46.dp
                        )
                        .height(50.dp)
                ) {

                }
            }


        }
    }
}

@Composable
fun FirsImageRowSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center
        ) {
            RoundedCornerImageView(
                painter = painterResource(id = R.drawable.ic_boat),
                shape = Shapes.medium,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(90.dp)
                    .height(80.dp)
                    .padding(end = PaddingHalf)
                    .border(
                        width = 1.dp,
                        color = SeaBlue400,
                        shape = Shapes.medium
                    )
            )
            Text(
                text = stringResource(R.string.catamaran),
                fontSize = 12.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                modifier = Modifier.padding(top = 3.dp)
            )
        }

        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center
        ) {
            RoundedCornerImageView(
                painter = painterResource(id = R.drawable.yacht),
                shape = Shapes.medium,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(90.dp)
                    .height(80.dp)
                    .padding(end = PaddingHalf)
                    .border(
                        width = 1.dp,
                        color = SeaBlue400,
                        shape = Shapes.medium
                    )
            )
            Text(
                text = stringResource(R.string.catamaran),
                fontSize = 12.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                modifier = Modifier.padding(top = 3.dp)
            )
        }
    }
}

@Composable
fun AvailableCareersSection(boatList: MutableList<Boat>) {
    Text(
        text = stringResource(R.string.your_available_careers),
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = fontFamily,
        color = Color.Black,
        textAlign = TextAlign.Left,
        modifier = Modifier
            .padding(vertical = 14.dp)
            .fillMaxWidth()
    )
    boatList.forEach { boat ->
        BoatListCard(
            boatImage = boat.boatImage,
            boatName = boat.boatName,
            boatLocation = boat.location,
            boatPrice = boat.price,
            isParked = boat.parkingStatus,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(top = 21.dp),
            locationFontWeight = FontWeight.Normal,
            paddingBetweenText = PaddingHalf,
            priceFontWeight = if (boat.parkingStatus) FontWeight.Normal else FontWeight.SemiBold,
            viewOnMapText = if (boat.parkingStatus) stringResource(R.string.view_on_map) else stringResource(
                R.string.not_parked
            ),
            parkingStatusText = if (boat.parkingStatus) stringResource(R.string.parked) else stringResource(
                R.string.park_now
            )
        )
    }
}


@Preview
@Composable
fun PreviewBoatListScreen() {
    BoatListScreen()
}
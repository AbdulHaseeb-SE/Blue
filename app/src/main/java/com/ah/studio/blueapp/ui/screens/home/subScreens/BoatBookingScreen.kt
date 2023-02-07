package com.ah.studio.blueapp.ui.screens.home.subScreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoatBookingScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = stringResource(R.string.bookings),
                navigationIconContentDescription = stringResource(id = R.string.back_button),
                actionIcons = {},
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = PaddingDouble,
                    end = PaddingDouble,
                    top = it.calculateTopPadding()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Text(
                    text = stringResource(R.string.catamaran_yatch),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = PaddingHalf)
                )
                CustomDropDown(
                    listItems = arrayOf(
                        stringResource(id = R.string.select_destination),
                        "First Destination",
                        "Second Destination"
                    ),
                    labelFontSize = 13.sp,
                    textFontSize = 17.sp,
                    trailingIcon = R.drawable.ic_arrow_right
                ) {}

                Text(
                    text = stringResource(R.string.select_date_time),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = PaddingLarge)
                )
                CustomCalendar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {}
            }
            item {
                Text(
                    text = stringResource(R.string.slots_available),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 27.dp)
                )
                TimeSlotTable(
                    startingSlot = "02:00",
                    endingSlot = "15:00",
                    startingTime = {},
                    endingTime = {},
                    modifier = Modifier
                        .padding(
                            top = 21.dp,
                        )
                        .fillMaxWidth()
                )
            }
            item {
                Button(
                    width = 0.dp,
                    height = 50.dp,
                    text = stringResource(id = R.string.next),
                    backgroundColor = SeaBlue400,
                    shape = Shapes.medium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(46.dp)
                ) {}
            }
        }
    }
}

@Preview
@Composable
fun PreviewBoatBookingScreen() {
    BoatBookingScreen()
}
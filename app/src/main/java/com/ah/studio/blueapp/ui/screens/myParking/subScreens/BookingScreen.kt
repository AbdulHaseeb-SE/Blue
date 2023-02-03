package com.ah.studio.blueapp.ui.screens.myParking.subScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.Divider
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
import java.time.LocalDate
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = stringResource(R.string.bookings),
                elevation = 0.dp,
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
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = stringResource(R.string.catamaran_boats),
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Color.Black
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            DatePickerRow()
            TimePickerRow()
            DashedDivider(
                thickness = 1.dp, color = OxfordBlue900,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PaddingHalf, vertical = 21.dp)
            )
            PriceCalculationRow()
            Button(
                width = 0.dp,
                height = 50.dp,
                text = stringResource(R.string.continue_text),
                backgroundColor = SeaBlue400,
                shape = Shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 28.dp,
                        end = 28.dp,
                        bottom = 50.dp,
                        top = 50.dp
                    ),
                fontWeight = FontWeight.SemiBold
            ) {}
        }
    }
}

@Composable
fun DatePickerRow() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingHalf, vertical = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.starting_date),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily,
            modifier = Modifier.padding(
                vertical = 12.dp
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            DatePicker(selectedDate = {})
            Divider(
                thickness = 1.dp,
                color = Grey300,
                modifier = Modifier
                    .width(11.dp)
                    .padding(start = 4.dp)
            )
            Text(
                text = stringResource(R.string.to),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey300,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            )
            Divider(
                thickness = 1.dp,
                color = Grey300,
                modifier = Modifier
                    .width(11.dp)
                    .padding(end = 4.dp)
            )
            DatePicker(
                currentDate = LocalDate.now().plusDays(5),
                selectedDate = {})
        }
    }
}

@Composable
fun TimePickerRow() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingHalf),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.starting_time),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily,
            modifier = Modifier.padding(
                vertical = 12.dp
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            TimePicker(selectedTime = {})
            Divider(
                thickness = 1.dp,
                color = Grey300,
                modifier = Modifier
                    .width(11.dp)
                    .padding(start = 4.dp)
            )
            Text(
                text = stringResource(R.string.to),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey300,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
            )
            Divider(
                thickness = 1.dp,
                color = Grey300,
                modifier = Modifier
                    .width(11.dp)
                    .padding(end = 4.dp)
            )
            TimePicker(
                currentTime = LocalTime.MIDNIGHT,
                selectedTime = {}
            )
        }
    }
}

@Composable
fun PriceCalculationRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingHalf),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.price_calculations),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = Color.Black
            )
            Text(
                text = "4.500 / Hour",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Color.Black,
                modifier = Modifier.padding(bottom = PaddingHalf)
            )
            BlueRoundedCornerShape(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(118.dp),
                borderColor = Grey500Percent20,
                containerColor = Grey200
            ) {
                Text(
                    text = ". KWD",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 12.dp,
                            horizontal = PaddingHalf
                        ),
                    textAlign = TextAlign.Center
                )
            }
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.total_parking_time),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 23.dp)
            )

            BlueRoundedCornerShape(
                modifier = Modifier
                    .wrapContentHeight()
                    .width(118.dp),
                borderColor = Grey500Percent20,
                containerColor = Grey200
            ) {
                Text(
                    text = "76 Hours",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 12.dp
                        ),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}


@Preview
@Composable
fun PreviewBookingScreen() {
    BookingScreen()
}
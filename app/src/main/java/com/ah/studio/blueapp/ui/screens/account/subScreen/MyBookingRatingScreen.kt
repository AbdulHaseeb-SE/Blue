package com.ah.studio.blueapp.ui.screens.account.subScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookingRatingScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = stringResource(R.string.my_bookings),
                elevation = 0.dp,
                navigationIconContentDescription = "",
                actionIcons = {},
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = PaddingDouble,
                    end = PaddingDouble,
                    top = it.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            LocationSection()
            CaptainBoatNameSection()
            TimeDateSection()
            SummarySection()

            Button(
                width = 0.dp,
                height = 50.dp,
                text = stringResource(R.string.rate_now),
                backgroundColor = SeaBlue400,
                shape = Shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 28.dp,
                        end = 28.dp,
                        bottom = 40.dp,
                        top = 50.dp
                    ),
                fontWeight = FontWeight.SemiBold
            ) {}
        }
    }
}

@Composable
fun CaptainBoatNameSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BlueRoundedCornerShape(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                shape = CircleShape,
                containerColor = SeaBlue08Percent,
                borderColor = SeaBlue400
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_captian_cap),
                        contentDescription = stringResource(R.string.clock_icon),
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(horizontal = 9.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.captain),
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "Salim Alsafi",
                    fontSize = 13.sp,
                    fontFamily = fontFamily,
                    color = Grey700,
                    textAlign = TextAlign.Start
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            BlueRoundedCornerShape(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                shape = CircleShape,
                containerColor = Color.Transparent,
                borderColor = Color.Transparent
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_boat),
                    contentDescription = stringResource(R.string.clock_icon),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = "Catamaran Boats",
                fontSize = 16.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 9.dp)
            )
        }
    }
}

@Composable
fun TimeDateSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .wrapContentWidth()
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = stringResource(R.string.clock_icon),
            )
            Text(
                text = "9 AM to 1 PM",
                fontSize = 14.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Grey700,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 9.dp)
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .wrapContentWidth()
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            BlueRoundedCornerShape(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                shape = CircleShape
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = stringResource(R.string.calendar_icon),
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )
                }
            }
            Text(
                text = "24 Sep 2022",
                fontSize = 14.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Grey700,
                modifier = Modifier
                    .padding(start = 9.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun LocationSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_circled_location),
            contentDescription = stringResource(R.string.location_icon),
        )

        Column(
            modifier = Modifier.padding(horizontal = 9.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.pick_up_address),
                fontSize = 17.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = "Marina Mall, Salmiya",
                fontSize = 14.sp,
                fontFamily = fontFamily,
                color = Grey700,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun SummarySection() {
    BlueRoundedCornerShape(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 22.dp)
    ) {
        Text(
            text = stringResource(R.string.summary),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily,
            color = Color.Black,
            modifier = Modifier.padding(
                top = 26.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 12.dp
            )
        )
        Row(
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Catamaran Boats",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey700
            )
            Text(
                text = "50.000 KWD",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Grey700
            )
        }
        Divider(
            thickness = 1.dp,
            color = Black19Percent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Chicken Burger",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey700
            )
            Text(
                text = "3.500 KWD",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Grey700
            )
        }
        Divider(
            thickness = 1.dp,
            color = Black19Percent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Mix Kabab",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey700
            )
            Text(
                text = "7.500 KWD",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Grey700
            )
        }
        Divider(
            thickness = 1.dp,
            color = Black19Percent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Pan Cakes",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey700
            )
            Text(
                text = "4.800 KWD",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Grey700
            )
        }
        Divider(
            thickness = 1.dp,
            color = Black19Percent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Chicken Pizza",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey700
            )
            Text(
                text = "5.000 KWD",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Grey700
            )
        }

        DashedDivider(
            thickness = 1.dp,
            color = Black19Percent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )

        Row(
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    bottom = 17.dp,
                    end = 10.dp
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.total_amount),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = OxfordBlue900
            )
            Text(
                text = "50.000 KWD",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = OxfordBlue900
            )
        }
    }
}

@Preview
@Composable
fun PreviewMyBookingRatingScreen() {
    MyBookingRatingScreen()
}
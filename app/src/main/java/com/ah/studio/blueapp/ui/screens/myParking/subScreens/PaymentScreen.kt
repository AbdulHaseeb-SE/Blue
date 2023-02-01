package com.ah.studio.blueapp.ui.screens.myParking.subScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
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
fun PaymentScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = stringResource(R.string.payment),
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
            BoatNameSection()
            TimeDateSection()

            SummarySection()
            CouponSection()
            PaymentMethodSection()

            Button(
                width = 0.dp,
                height = 50.dp,
                text = stringResource(R.string.pay_and_confirm),
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
fun BoatNameSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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
            fontSize = 14.sp,
            fontFamily = fontFamily,
            color = Color.Black,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 9.dp)
        )
    }
}

@Composable
fun TimeDateSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = stringResource(R.string.clock_icon),
            )
            Text(
                text = "9 AM to 1 PM",
                fontSize = 14.sp,
                fontFamily = fontFamily,
                color = Black25Percent,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 9.dp)
            )
        }

        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(vertical = 15.dp),
            verticalAlignment = Alignment.CenterVertically
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
                        .fillMaxSize(),
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
                color = Black25Percent,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 9.dp)
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
                text = stringResource(R.string.parking_address),
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
                color = Black25Percent,
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
                vertical = 12.dp,
                horizontal = 12.dp
            )
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
                text = "4 Days",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey700
            )
            Text(
                text = "50.000",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = Grey700
            )
        }

        DashedDivider(
            thickness = 1.dp,
            color = Black19Percent,
            modifier = Modifier.fillMaxWidth()
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
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = OxfordBlue900
            )
            Text(
                text = "50.000",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = Grey700
            )
        }
    }
}

@Composable
fun CouponSection() {
    Box(
        modifier = Modifier
            .padding(bottom = 45.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        CustomTextField(
            label = "Discount",
            placeholder = "Coupon Code",
            value = {}
        )
        Button(
            width = 112.dp,
            height = 50.dp,
            text = "Apply",
            backgroundColor = SeaBlue400,
            shape = Shapes.medium,
            fontWeight = FontWeight.Bold
        ) {}
    }
}

@Composable
fun PaymentMethodSection() {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.payment_method),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 21.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedCornerImageView(
                painter = painterResource(id = R.drawable.ic_visa),
                shape = Shapes.medium,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(69.dp)
                    .background(Color.White)
            )
            RoundedCornerImageView(
                painter = painterResource(id = R.drawable.ic_master_card),
                shape = Shapes.medium,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(69.dp)
                    .background(Color.White)
            )
            RoundedCornerImageView(
                painter = painterResource(id = R.drawable.ic_knet),
                shape = Shapes.medium,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(69.dp)
                    .background(Color.White)
            )
        }
    }
}


@Preview
@Composable
fun PreviewPaymentScreen() {
    PaymentScreen()
}
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.screens.home.domain.dto.PaymentMethod
import com.ah.studio.blueapp.ui.screens.myParking.ParkingViewModel
import com.ah.studio.blueapp.ui.screens.myParking.domain.dto.parkingList.Boat
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkingPaymentScreen(
    id: Int,
    selectedDate: String?,
    startingTime: String?,
    endingTime: String?,
    price: Double,
    onBackClick: () -> Unit,
    viewModel: ParkingViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    var parkingList: List<Boat> by remember {
        mutableStateOf(emptyList())
    }
    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getParkingListResponse(1)
            viewModel.boatsAvailableToParkResponse.collectLatest { list ->
                if (list != null) {
                    parkingList = list.data
                    isLoading = false
                }
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = stringResource(R.string.payment),
                navigationIconContentDescription = "",
                actionIcons = {},
                onNavigationIconClick = {
                    onBackClick()
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Box {
            if (parkingList.isNotEmpty()) {
                parkingList.forEach { boat ->
                    if (boat.id == id) {
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

                            LocationSection(boat)
                            BoatNameSection(boat)
                            TimeDateSection(selectedDate, startingTime, endingTime)
                            SummarySection(price)
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
            }
            if (isLoading) {
                CircularProgressBar()
            }
        }
    }
}

@Composable
fun BoatNameSection(boat: Boat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = PaddingHalf),
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
                painter = coilImageLoadingAsync(imageUrl = boat.image),
                contentDescription = stringResource(R.string.clock_icon),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = boat.name,
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
fun TimeDateSection(selectedDate: String?, startingTime: String?, endingTime: String?) {
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
                text = "$startingTime to $endingTime",
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
                text = selectedDate.toString(),
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
fun LocationSection(boat: Boat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = PaddingHalf),
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
                text = boat.address,
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
fun SummarySection(price: Double) {
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

        /*Row(
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
*/
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
                text = "${String.format("%.2f", price)} KWD",
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
            PaymentMethod(
                methodList = listOf(
                    PaymentMethod(
                        name = "Visa Card",
                        image = painterResource(id = R.drawable.ic_visacard)
                    ),
                    PaymentMethod(
                        name = "Master Card",
                        image = painterResource(id = R.drawable.ic_mastercard)
                    ),
                    PaymentMethod(
                        name = "KNET Card",
                        image = painterResource(id = R.drawable.ic_knet)
                    )
                )
            )
        }
    }
}

/*
@Preview
@Composable
fun PreviewPaymentScreen() {
    PaymentScreen()
}*/

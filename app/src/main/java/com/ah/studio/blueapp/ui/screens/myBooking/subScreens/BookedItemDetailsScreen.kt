package com.ah.studio.blueapp.ui.screens.myBooking.subScreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BlueRoundedCornerShape
import com.ah.studio.blueapp.ui.component.CircularProgressBar
import com.ah.studio.blueapp.ui.component.DashedDivider
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.myBooking.BoatBookingViewModel
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatDetails.BookedBoatDetail
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatDetails.BookedBoatDetailsResponse
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookedItemDetailsScreen(
    id: String,
    viewModel: BoatBookingViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    var bookedBoatDetailsResponse: BookedBoatDetailsResponse? by remember {
        mutableStateOf(null)
    }
    var bookedBoatDetails: BookedBoatDetail? by remember {
        mutableStateOf(null)
    }
    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getBookedBoatDetailResponse(if (id.contains("#")) id.substring(1) else id)
            viewModel.bookedBoatDetailsResponse.collectLatest { response ->
                if (response != null) {
                    bookedBoatDetailsResponse = response
                    bookedBoatDetails = response.data
                    isLoading = false
                }
            }
        }
    }
    Log.d("CheckBookedBoatId", id)
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = stringResource(R.string.my_bookings),
                navigationIconContentDescription = "",
                actionIcons = {},
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Box {
            if (id != "null") {
                if (bookedBoatDetailsResponse != null && bookedBoatDetails != null) {
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
                        verticalArrangement = Arrangement.Top
                    ) {
                        BookedBoatLocationSection(bookedBoatDetails!!)
                        BookedBoatCaptainBoatNameSection(bookedBoatDetails!!)
                        BookedBoatTimeDateSection(bookedBoatDetails!!)
                        BookedBoatSummarySection(bookedBoatDetails!!)
                    }
                }
            } else {
                isLoading = false
                Text(
                    text = "Data Not Found!! \n The Id for this booked item in not valid.",
                    fontSize = 14.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Grey700,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(it.calculateTopPadding() + PaddingDouble)
                        .fillMaxSize()
                )
            }
            if (isLoading) {
                CircularProgressBar()
            }
        }
    }
}

@Composable
fun BookedBoatCaptainBoatNameSection(bookedBoatDetails: BookedBoatDetail) {
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
                        contentDescription = stringResource(R.string.captain),
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
                )/*
                Text(
                    text = "",
                    fontSize = 13.sp,
                    fontFamily = fontFamily,
                    color = Grey700,
                    textAlign = TextAlign.Start
                )*/
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
                    painter = coilImageLoadingAsync(imageUrl = bookedBoatDetails.image),
                    contentDescription = stringResource(R.string.boat_Image),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = bookedBoatDetails.name,
                fontSize = 16.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = 9.dp)
            )
        }
    }
}

@Composable
fun BookedBoatTimeDateSection(bookedBoatDetails: BookedBoatDetail) {
    val inputTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputTimeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val inputDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
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
                text = "${
                    inputTimeFormat.parse(bookedBoatDetails.start)
                        ?.let { outputTimeFormat.format(it) }
                } to ${
                    inputTimeFormat.parse(bookedBoatDetails.end)
                        ?.let { outputTimeFormat.format(it) }
                }",
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
                text = inputDateFormat.parse(bookedBoatDetails.start)
                    ?.let { outputDateFormat.format(it) }.toString(),
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
fun BookedBoatLocationSection(bookedBoatDetails: BookedBoatDetail) {
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
                text = bookedBoatDetails.pickup_address,
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
fun BookedBoatSummarySection(bookedBoatDetails: BookedBoatDetail) {
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
                top = 20.dp,
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
                text = bookedBoatDetails.name,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey700
            )
            Text(
                text = "${bookedBoatDetails.total} KWD",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Grey700
            )
        }

        if (bookedBoatDetails.package_name != ""){
            Divider(
                thickness = 1.dp,
                color = Black19Percent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 8.dp)
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
                    text = bookedBoatDetails.package_name,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fontFamily,
                    color = Grey700
                )
                Text(
                    text = "${bookedBoatDetails.package_price} KWD",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                    color = Grey700
                )
            }
        }
        if (bookedBoatDetails.product.isNotEmpty()){
            bookedBoatDetails.product.forEach { product ->
                Divider(
                    thickness = 1.dp,
                    color = Black19Percent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 8.dp)
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
                        text = product.name,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fontFamily,
                        color = Grey700
                    )
                    Text(
                        text = "${product.grand_total} KWD",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Grey700
                    )
                }
            }
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
                .padding(
                    start = 10.dp,
                    end = 10.dp
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.coupon_amount),
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey700
            )
            Text(
                text = "${bookedBoatDetails.coupon_amount ?: 0} KWD",
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
                text = "${bookedBoatDetails.grand_total} KWD",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = OxfordBlue900
            )
        }
    }
}

package com.ah.studio.blueapp.ui.screens.myParking.subScreens

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BlueRoundedCornerShape
import com.ah.studio.blueapp.ui.component.CircularProgressBar
import com.ah.studio.blueapp.ui.component.DashedDivider
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.myParking.ParkingViewModel
import com.ah.studio.blueapp.ui.screens.myParking.domain.dto.myParkingBooking.ParkedBoat
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
fun ParkedBoatDetailScreen(
    id: Int,
    viewModel: ParkingViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    var parkingList: List<ParkedBoat> by remember {
        mutableStateOf(emptyList())
    }
    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getParkingBookingListResponse(1)
            viewModel.parkingBookingListResponse.collectLatest { list ->
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
                text = stringResource(R.string.parking_details),
                navigationIconContentDescription = "",
                actionIcons = {},
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Box {
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
                if (parkingList.isNotEmpty()) {
                    isLoading = false
                    parkingList.forEach { parkedBoat ->
                        if (parkedBoat.id == id) {
                            BoatLocationSection(parkedBoat)
                            BoatTimeDateSection(parkedBoat)
                            BoatSummarySection(parkedBoat)
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
fun BoatLocationSection(parkedBoat: ParkedBoat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_circled_location),
            contentDescription = stringResource(R.string.location_icon),
            modifier = Modifier.size(40.dp)
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
                text = parkedBoat.address,
                fontSize = 14.sp,
                fontFamily = fontFamily,
                color = Grey700,
                textAlign = TextAlign.Start,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_circled_location),
            contentDescription = stringResource(R.string.location_icon),
            modifier = Modifier.size(40.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = PaddingHalf)
                .fillMaxWidth(0.4f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.parking_name),
                fontSize = 17.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = parkedBoat.parking_name,
                fontSize = 14.sp,
                fontFamily = fontFamily,
                color = Grey700,
                textAlign = TextAlign.Start,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        CaptainBoatNameSection(parkedBoat)
    }
}

@Composable
fun CaptainBoatNameSection(parkedBoat: ParkedBoat) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .wrapContentHeight()
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
                painter = coilImageLoadingAsync(imageUrl = parkedBoat.image),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = parkedBoat.boat_name,
            fontSize = 16.sp,
            fontFamily = fontFamily,
            color = Color.Black,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = PaddingHalf)
        )
    }
}

@Composable
fun BoatTimeDateSection(parkedBoat: ParkedBoat) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val outputTimeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())

    val fromDateTime = inputFormat.parse(parkedBoat.from_date)
    val toDateTime = inputFormat.parse(parkedBoat.to_date)

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
                text = fromDateTime?.let { outputDateFormat.format(it) }.toString(),
                fontSize = 14.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Grey700,
                maxLines = 1,
                modifier = Modifier
                    .padding(start = PaddingHalf)
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
            Image(
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = stringResource(R.string.clock_icon),
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "${fromDateTime?.let { outputTimeFormat.format(it) }} to ${
                    toDateTime?.let {
                        outputTimeFormat.format(
                            it
                        )
                    }
                }",
                fontSize = 14.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Grey700,
                textAlign = TextAlign.Start,
                maxLines = 2,
                modifier = Modifier
                    .padding(start = PaddingHalf)
                    .fillMaxWidth()
            )
        }
    }
}


@Composable
fun BoatSummarySection(parkedBoat: ParkedBoat) {
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
                text = "${parkedBoat.grand_total} KWD",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = OxfordBlue900
            )
        }
    }
}

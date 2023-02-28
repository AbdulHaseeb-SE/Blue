package com.ah.studio.blueapp.ui.screens.myParking

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BookedItem
import com.ah.studio.blueapp.ui.component.Button
import com.ah.studio.blueapp.ui.component.CircularProgressBar
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.myParking.domain.dto.myParkingBooking.ParkedBoat
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyParkingScreen(
    onAddBoatClick: () -> Unit,
    onParkedBoatClick: (id: Int) -> Unit,
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
                navigationIcon = null,
                navigationIconContentDescription = "",
                text = stringResource(id = R.string.my_parkings),
                actionIcons = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add), contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(PaddingHalf)
                            .clickable {
                                onAddBoatClick()
                            }
                    )
                },
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        containerColor = Color.White,
    ) { paddingValues ->
        Box {
            Column(
                modifier = Modifier
                    .padding(
                        top = paddingValues.calculateTopPadding() + 24.dp,
                        start = PaddingDouble,
                        end = PaddingDouble
                    )
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                if (parkingList.isNotEmpty()) {
                    LazyColumn {
                        itemsIndexed(
                            parkingList
                        ) { index, item ->
                            isLoading = false
                            BookedItem(
                                itemName = item.parking_name,
                                itemDescription = "",
                                itemImage = coilImageLoadingAsync(imageUrl = item.image),
                                price = "${item.price} KWD",
                                bookingId = "Booking id: ${if (item.booking_id_string != null) item.booking_id_string else index.toString()}",
                                bookingStatus = stringResource(id = R.string.parked),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 28.dp)
                                    .clickable {
                                        onParkedBoatClick(item.id)
                                    }
                            )
                        }
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = 26.dp,
                                vertical = 130.dp
                            )
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.boat_illustration),
                            contentDescription = stringResource(
                                R.string.boat_Image
                            ),
                            modifier = Modifier.padding(horizontal = 29.dp)
                        )
                        Text(
                            text = stringResource(R.string.no_boat_parked),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(
                                top = PaddingTripleLarge,
                            )
                        )

                        Button(
                            width = 283.dp,
                            height = 50.dp,
                            text = stringResource(R.string.park_now),
                            backgroundColor = SeaBlue400,
                            shape = Shapes.medium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = PaddingDouble)
                        ) {
                            onAddBoatClick()
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

package com.ah.studio.blueapp.ui.screens.myParking.subScreens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
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
import com.ah.studio.blueapp.ui.component.*
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
fun BoatListScreen(
    onParkNowClick: (id: Int) -> Unit,
    onBackButtonClick: () -> Unit,
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
                navigationIconContentDescription = "",
                text = stringResource(id = R.string.my_boats),
                actionIcons = { },
                onNavigationIconClick = {
                    onBackButtonClick()
                }
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
                        top = paddingValues.calculateTopPadding(),
                        start = PaddingDouble,
                        end = PaddingDouble
                    )
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                if (parkingList.isNotEmpty()) {
                    Text(
                        text = stringResource(R.string.select_which_boat_to_park),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontFamily,
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(vertical = 14.dp)
                            .fillMaxWidth()
                    )
                    LazyColumn {
                        itemsIndexed(
                            parkingList
                        ) { _, item ->
                            isLoading = false
                            BoatListCard(
                                boatImage = coilImageLoadingAsync(imageUrl = item.image),
                                boatName = item.name,
                                boatLocation = item.address,
                                boatPrice = "",
                                isParked = item.parking_status == "parked",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(140.dp)
                                    .padding(top = 21.dp),
                                locationFontWeight = FontWeight.Normal,
                                paddingBetweenText = PaddingHalf,
                                priceFontWeight = if (item.parking_status == "parked") FontWeight.Normal else FontWeight.SemiBold,
                                viewOnMapText = if (item.parking_status == "parked") "" else stringResource(
                                    R.string.not_parked
                                ),
                                parkingStatusText = if (item.parking_status == "parked") stringResource(
                                    R.string.parked
                                ) else stringResource(
                                    R.string.park_now
                                ),
                                onParkNowClick = {
                                    onParkNowClick(item.id)
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
                            text = stringResource(R.string.no_boat_message),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(
                                top = PaddingTripleLarge,
                            )
                        )
                    }
                }
            }
            if (isLoading) {
                CircularProgressBar()
            }
        }
    }
}
package com.ah.studio.blueapp.ui.screens.myParking.subScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
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
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(
    id: Int,
    selectedDate: String?,
    startingTime: String?,
    endingTime: String?,
    price: Double,
    onBackButtonClick: () -> Unit,
    onNextButtonClick: (id:Int, selectedDate: String?, startingTime: String?, endingTime: String?, price: Double) -> Unit,
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
                text = "",
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
                onNavigationIconClick = { onBackButtonClick() })
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            if (parkingList.isNotEmpty()) {
                parkingList.forEach { boat ->
                    if (boat.id == id) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .background(Color.White),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            //            Map()
                            BottomCardSection(
                                boat,
                                price,
                                onNextButtonClick = {
                                    onNextButtonClick(id, selectedDate, startingTime, endingTime, price)
                                }
                            )
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
fun BottomCardSection(
    boat: Boat,
    price: Double,
    onNextButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(208.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.TopEnd
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            androidx.compose.material3.Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(207.dp)
                    .padding(top = 30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            ) {
                Card(
                    boatImage = coilImageLoadingAsync(imageUrl = boat.image),
                    parkingSlotName = boat.name,
                    boatLocation = boat.address,
                    boatPrice = "$price KWD",
                    rating = 0f,
                    priceFontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(
                            top = 24.dp,
                            start = PaddingDouble,
                            bottom = PaddingDouble
                        ),
                    paddingBetweenText = 14.dp
                )
            }
        }
        BlueRoundedCornerShape(
            modifier = Modifier
                .wrapContentSize()
                .padding(end = PaddingLarge)
                .clickable {
                    onNextButtonClick()
                },
            borderColor = SeaBlue400,
            containerColor = SeaBlue400,
            shape = CircleShape
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.size(60.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.next),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(PaddingHalf)
                )
            }
        }
    }
}

@Composable
fun Card(
    boatImage: Painter,
    parkingSlotName: String,
    boatLocation: String,
    boatPrice: String,
    rating: Float,
    modifier: Modifier = Modifier,
    titleFontWeight: FontWeight = FontWeight.SemiBold,
    locationFontWeight: FontWeight = FontWeight(600),
    priceFontWeight: FontWeight = FontWeight.Normal,
    paddingBetweenText: Dp = 11.dp
) {
    Row(
        modifier = modifier.background(Color.White)
    ) {
        RoundedCornerImageView(
            painter = boatImage,
            shape = Shapes.medium,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(0.35f),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = parkingSlotName,
                fontSize = 17.sp,
                fontWeight = titleFontWeight,
                fontFamily = fontFamily,
                color = Color.Black,
                modifier = Modifier.padding(bottom = paddingBetweenText)
            )

            LocationComponent(
                painterResource(id = R.drawable.ic_location_grey),
                locationText = boatLocation,
                locationStartPadding = 6.dp,
                rowTopPadding = 0.dp,
                iconPaddingStart = 0.dp,
                modifier = Modifier
                    .padding(0.dp)
                    .height(20.dp),
                locationTextFontWeight = locationFontWeight
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = boatPrice,
                    fontSize = 17.sp,
                    fontWeight = priceFontWeight,
                    fontFamily = fontFamily,
                    color = OxfordBlue900,
                    modifier = Modifier.padding(top = paddingBetweenText)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star_outlined),
                        contentDescription = stringResource(R.string.rating_star),
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                    )
                    Text(
                        text = rating.toString(),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fontFamily,
                        color = OxfordBlue900,
                        modifier = Modifier.padding(start = 7.dp)
                    )
                }
            }
        }
    }
}

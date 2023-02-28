package com.ah.studio.blueapp.ui.screens.myParking.subScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.*
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
import com.ah.studio.blueapp.ui.screens.myParking.ParkingViewModel
import com.ah.studio.blueapp.ui.screens.myParking.domain.dto.parkingList.Boat
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    id: Int,
    onBackButtonCLick: () -> Unit,
    onContinueButtonClick: (selectedDate: String?, startingTime: String?, endingTime: String?, price: Double) -> Unit,
    viewModel: ParkingViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    var parkingList: List<Boat> by remember {
        mutableStateOf(emptyList())
    }
    var startingTime: LocalTime? by remember {
        mutableStateOf(LocalTime.now())
    }
    var endingTime: LocalTime? by remember {
        mutableStateOf(LocalTime.now())
    }
    var selectedDate: LocalDate? by remember {
        mutableStateOf(LocalDate.now())
    }
    var price: Double by remember {
        mutableStateOf(0.0)
    }
    var duration by remember {
        mutableStateOf(Duration.ZERO.toHours().toDouble())
    }
    val inputTimeFormat =
        SimpleDateFormat("HH:mm", Locale.getDefault())
    val outputTimeFormat =
        SimpleDateFormat("hh:mm a", Locale.getDefault())
    val inputDateFormat =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputDateFormat =
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault())


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

    SideEffect {
        duration = calculateDuration(startingTime, endingTime)
        price = calculatePrice(duration)
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var snackbar by remember { mutableStateOf("") }
    var showSnackBar by remember { mutableStateOf(false) }
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                if (showSnackBar) {
                    Snackbar(
                        actionColor = SeaBlue400,
                        contentColor = Color.Black,
                        snackbarData = data,
                        containerColor = Color.White,
                        shape = RoundedCornerShape(50.dp)
                    )
                }
            }
        },
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = stringResource(R.string.bookings),
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
                onNavigationIconClick = { onBackButtonCLick() })
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
                                    bottom = PaddingDouble,
                                    top = it.calculateTopPadding() + PaddingHalf
                                )
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Text(
                                text = boat.name,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = fontFamily,
                                color = Color.Black
                            )
                            RoundedCornerImageView(
                                painter = coilImageLoadingAsync(imageUrl = boat.image),
                                contentScale = ContentScale.Crop,
                                shape = Shapes.medium,
                                modifier = Modifier
                                    .padding(
                                        top = PaddingDouble,
                                        bottom = PaddingHalf,
                                    )
                                    .height(160.dp)
                            )
                            DatePickerRow(selectedDate = { date ->
                                selectedDate = date
                            })
                            TimePickerRow(
                                startingTime = { startTime ->
                                    startingTime = startTime
                                    duration = calculateDuration(startingTime, endingTime)
                                    price = calculatePrice(duration)
                                },
                                endingTime = { endTime ->
                                    if (endTime > startingTime) {
                                        endingTime = endTime
                                        duration = calculateDuration(startingTime, endingTime)
                                        price = calculatePrice(duration)
                                    } else {
                                        snackbar =
                                            "Ending time must be greater than the starting time, please pick again!"
                                        showSnackBar = true
                                    }
                                }
                            )
                            DashedDivider(
                                thickness = 1.dp,
                                color = OxfordBlue900,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = PaddingHalf, vertical = 21.dp)
                            )
                            PriceCalculationRow(price, duration)
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
                            ) {
                                if (price > 0.0 && duration > 0.0) {
                                    onContinueButtonClick(
                                        inputDateFormat.parse(selectedDate.toString())
                                            ?.let { it1 -> outputDateFormat.format(it1) },
                                        inputTimeFormat.parse(
                                            startingTime.toString()
                                        )?.let { it1 -> outputTimeFormat.format(it1) },
                                        inputTimeFormat.parse(
                                            endingTime.toString()
                                        )?.let { it1 -> outputTimeFormat.format(it1) },
                                        price
                                    )
                                } else {
                                    snackbar = "Please Choose the valid Duration Time!!"
                                    showSnackBar = true
                                }
                            }
                        }
                    }
                }
            }

            if (isLoading) {
                CircularProgressBar()
            }
        }
    }
    if (showSnackBar) {
        SideEffect {
            scope.launch {
                snackbarHostState.showSnackbar(
                    snackbar, duration = SnackbarDuration.Short
                )
                showSnackBar = false
            }
        }
    }
}

fun calculateDuration(startingTime: LocalTime?, endingTime: LocalTime?): Double =
    (Duration.between(startingTime, endingTime).toMinutes().toDouble()) / 60

fun calculatePrice(duration: Double): Double = (duration * 4.500)

@Composable
fun DatePickerRow(
    selectedDate: (LocalDate) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingHalf, vertical = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.date),
            fontSize = 17.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily,
            modifier = Modifier.padding(
                vertical = 12.dp
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            DatePicker(
                modifier = Modifier.fillMaxWidth(),
                selectedDate = {
                    selectedDate(it)
                }
            )
        }
    }
}

@Composable
fun TimePickerRow(
    startingTime: (LocalTime) -> Unit,
    endingTime: (LocalTime) -> Unit
) {
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
            color = Color.Black,
            modifier = Modifier.padding(
                vertical = 12.dp
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TimePicker(
                selectedTime = { startingTime ->
                    startingTime(startingTime)
                }
            )
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
                    .padding(horizontal = 5.dp)
            )
            Divider(
                thickness = 1.dp,
                color = Grey300,
                modifier = Modifier
                    .width(11.dp)
                    .padding(end = 4.dp)
            )
            TimePicker(
                currentTime = LocalTime.now(),
                selectedTime = { endingTime ->
                    endingTime(endingTime)
                }
            )
        }
    }
}

@Composable
fun PriceCalculationRow(price: Double, duration: Double) {
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
                    text = "${String.format("%.2f", price)} KWD",
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
                    text = "${String.format("%.2f", duration)} Hours",
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
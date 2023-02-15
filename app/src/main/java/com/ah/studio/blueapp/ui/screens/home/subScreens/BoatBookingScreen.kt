package com.ah.studio.blueapp.ui.screens.home.subScreens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.ah.studio.blueapp.ui.screens.home.HomeViewModel
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails.BoatDetails
import com.ah.studio.blueapp.ui.screens.home.domain.dto.timeSlot.Time
import com.ah.studio.blueapp.ui.screens.home.domain.dto.timeSlot.TimeSlotBody
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.trimTimeToHrMin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun BoatBookingScreen(
    boatId: Int,
    destinationID: Int? = null,
    selectDestinationClick: () -> Unit,
    onNextClick: () -> Unit,
    viewModel: HomeViewModel = getKoin().get()
) {
    var isLoading by remember { mutableStateOf(true) }
    var boatDetails: BoatDetails? by remember { mutableStateOf(null) }
    var timeSlotResponse: List<Time>? by remember { mutableStateOf(null) }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var snackbar by remember { mutableStateOf("") }
    var showSnackBar by remember { mutableStateOf(false) }
    var selectedDestinationAddress by remember { mutableStateOf("") }
    var isFetchingTime by remember { mutableStateOf(false) }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewModel.getBoatDetailsResponse(boatId = boatId)
                viewModel.boatDetailsResponse.collectLatest { response ->
                    response?.data?.forEach {
                        boatDetails = it
                    }
                }
            } catch (e: Exception) {
                Log.e("Exception", "${e.message.toString()} ${e.cause} ${e.localizedMessage}")
            }
        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                if (showSnackBar) {
                    Snackbar(
                        actionColor = SeaBlue400,
                        contentColor = Color.Black,
                        snackbarData = data,
                        backgroundColor = Color.White,
                        shape = RoundedCornerShape(50.dp),
                        elevation = 2.dp
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
                navigationIconContentDescription = stringResource(id = R.string.back_button),
                actionIcons = {},
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        boatDetails?.destination_address?.forEach { destination ->
            selectedDestinationAddress =
                if ((destinationID != null && destination.id == destinationID)) {
                    destination.destination_address
                } else {
                    stringResource(id = R.string.select_destination)
                }
        }
        Box {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = PaddingDouble,
                        end = PaddingDouble,
                        top = it.calculateTopPadding()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    boatDetails?.name?.let { name ->
                        isLoading = false
                        Text(
                            text = name,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = PaddingHalf)
                        )
                    }
                    CustomTextField(
                        label = stringResource(id = R.string.select_destination),
                        placeholder = selectedDestinationAddress,
                        textInput = "",
                        readOnly = true,
                        labelFontSize = 13.sp,
                        textFontSize = 17.sp,
                        trailingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_right),
                                contentDescription = "",
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp)
                                    .clickable {
                                        selectDestinationClick()
                                    }
                            )
                        },
                        trailingIconColor = SeaBlue400,
                        modifier = Modifier
                            .padding(top = PaddingLarge)
                            .clickable {
                                selectDestinationClick()
                            }
                    ) {}
                    Text(
                        text = stringResource(R.string.select_date_time),
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        fontFamily = fontFamily,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = PaddingLarge)
                    )
                    CustomCalendar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) { selectedDate ->
                        timeSlotResponse = listOf()
                        isFetchingTime = true
                        if (destinationID != null) {
                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    viewModel.getAvailableTimeSlotResponse(
                                        TimeSlotBody(
                                            boat_id = boatId.toString(),
                                            start = "$selectedDate ${
                                                if (selectedDate == LocalDate.now()) LocalTime.now()
                                                    .toString() else "00:00"
                                            }",
                                            end = "$selectedDate 24:00"
                                        )
                                    )
                                    isFetchingTime = true
                                    viewModel.availableTimeSlotResponse.collectLatest { response ->
                                        timeSlotResponse =
                                            response?.data?.get(selectedDate.toString())
                                        Log.d("CheckTimeSlot", timeSlotResponse.toString())
                                    }
                                } catch (e: Exception) {
                                    Log.d("CheckDateTime", "in exception ${e.message}")
                                }
                            }
                        } else {
                            isFetchingTime = false
                            snackbar = "Please select the destination first !!"
                            showSnackBar = true
                        }
                    }
                }
                item {
                    Box {
                        if (!timeSlotResponse.isNullOrEmpty()) {
                            isFetchingTime = false
                            Text(
                                text = stringResource(R.string.slots_available),
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                fontFamily = fontFamily,
                                color = Color.Black,
                                textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 27.dp)
                            )
                            boatDetails?.destination_address?.forEach { destination ->
                                TimeSlotTable(
                                    startingSlot = trimTimeToHrMin(timeSlotResponse!!.first().date) + ":00",
                                    endingSlot = trimTimeToHrMin(timeSlotResponse!!.last().date) + ":00",
                                    startingTime = {},
                                    endingTime = {},
                                    duration = if ((destinationID != null && destination.id == destinationID)) {
                                        destination.destination_hrs
                                    } else {
                                        0
                                    },
                                    modifier = Modifier
                                        .padding(
                                            top = 21.dp,
                                        )
                                        .fillMaxWidth()
                                )
                            }
                        }
                        if (isFetchingTime) {
                            CircularProgressBar()
                        }
                    }

                }
                item {
                    Button(
                        width = 0.dp,
                        height = 50.dp,
                        text = stringResource(id = R.string.next),
                        backgroundColor = SeaBlue400,
                        shape = Shapes.medium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(46.dp)
                    ) {
                        onNextClick()
                    }
                }
            }
            if (isLoading) {
                CircularProgressBar()
            }
        }
    }

    if (showSnackBar) {
        LaunchedEffect(key1 = true) {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    snackbar, duration = SnackbarDuration.Short
                )
                showSnackBar = false
            }
        }
    }

}

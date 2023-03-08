package com.ah.studio.blueapp.ui.screens.myBooking.subScreens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.screens.myBooking.BoatBookingViewModel
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatDetails.BookedBoatDetail
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.bookedBoatDetails.BookedBoatDetailsResponse
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.review.AddReviewBody
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookedItemDetailsScreen(
    id: String,
    onBackButtonClick: () -> Unit,
    viewModel: BoatBookingViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    var isReviewLoading by remember {
        mutableStateOf(false)
    }
    var bookedBoatDetailsResponse: BookedBoatDetailsResponse? by remember {
        mutableStateOf(null)
    }
    var bookedBoatDetails: BookedBoatDetail? by remember {
        mutableStateOf(null)
    }
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )
    val scope = rememberCoroutineScope()
    var snackbar by remember { mutableStateOf("") }
    var showSnackBar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }


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
    BottomSheetScaffold(
        snackbarHost = {
            androidx.compose.material3.SnackbarHost(snackbarHostState) { data ->
                if (showSnackBar) {
                    androidx.compose.material3.Snackbar(
                        actionColor = SeaBlue400,
                        contentColor = Color.Black,
                        snackbarData = data,
                        containerColor = Color.White,
                        shape = RoundedCornerShape(50.dp)
                    )
                }
            }
        },
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetContent(
                onCloseButtonClick = {
                    scope.launch {
                        bottomSheetState.collapse()
                    }
                },
                onAddReviewButtonClick = { rating, feedback ->
                    isReviewLoading = true
                    scope.launch {
                        viewModel.getAddReviewResponse(
                            AddReviewBody(
                                boat_product_booking_id = id,
                                description = feedback,
                                entity_id = id,
                                rating = rating.toString(),
                                entity_type = "boat"
                            )
                        )
                        viewModel.addReviewResponse.collectLatest {response->
                            if (response != null){
                                isReviewLoading = false
                                showSnackBar = true
                                snackbar = response.message
                            }
                        }
                    }
                }
            )
        },
        sheetElevation = 16.dp,
        sheetShape = Shapes.medium,
        sheetPeekHeight = 0.dp,
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = stringResource(R.string.my_bookings),
                navigationIconContentDescription = "",
                actionIcons = {},
                onNavigationIconClick = {
                    onBackButtonClick()
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.White
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
                        Button(
                            width = 0.dp,
                            height = 50.dp,
                            text = stringResource(id = R.string.rate_now),
                            backgroundColor = SeaBlue400,
                            shape = Shapes.medium,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 46.dp,
                                    end = 46.dp,
                                    bottom = PaddingDouble,
                                    top = 46.dp
                                )
                        ) {
                            scope.launch {
                                if (bottomSheetState.isCollapsed) bottomSheetState.expand()
                            }
                        }
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
            if (isReviewLoading) {
                CircularProgressBar()
            }
        }
    }
    if (showSnackBar) {
        LaunchedEffect(key1 = true) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    snackbar, duration = SnackbarDuration.Short
                )
                showSnackBar = false
            }
        }
    }
}

@Composable
fun BottomSheetContent(
    onCloseButtonClick: () -> Unit,
    onAddReviewButtonClick: (rating: Int, feedback: String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth(),
        color = Color.White
    ) {
        var rating by remember { mutableStateOf(0) }
        var feedbackText by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = PaddingDouble),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.rate_your_experience),
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = PaddingHalf),
                    color = OxfordBlue900
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .width(60.dp)
                        .padding(vertical = PaddingHalf)
                        .clickable {
                            onCloseButtonClick()
                        }
                )
            }
            Divider(
                color = Grey200
            )
            Text(
                text = stringResource(R.string.rating),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = PaddingHalf)
            )

            RatingBar(
                rating = rating,
                onRatingChanged = { newRating ->
                    rating = newRating
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = PaddingDouble)
            )

            Log.d("CheckRating", rating.toString())

            Text(
                text = stringResource(R.string.feedback),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = Color.Black,
            )

            BlueRoundedCornerShape(
                containerColor = SeaBlue08Percent,
                borderColor = SeaBlue400,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = PaddingHalf)
                    .wrapContentHeight()
                    .animateContentSize()
            ) {
                TextField(
                    value = feedbackText,
                    onValueChange = { newText ->
                        feedbackText = newText
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.write_your_feedback_here),
                            fontSize = 15.sp,
                            fontFamily = fontFamily
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.Black,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = fontFamily
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Default
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp)
                )
            }

            Button(
                width = 0.dp,
                height = 50.dp,
                text = stringResource(id = R.string.add_review),
                backgroundColor = SeaBlue400,
                shape = Shapes.medium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 46.dp,
                        end = 46.dp,
                        bottom = 100.dp,
                        top = 46.dp
                    )
            ) {
                onAddReviewButtonClick(rating, feedbackText)
            }
        }
    }
}

@Composable
fun RatingBar(
    rating: Int,
    onRatingChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(5) { index ->
            IconButton(
                onClick = { onRatingChanged(index + 1) },
                modifier = Modifier
                    .padding(4.dp)
                    .size(28.dp)
            ) {
                if (index < rating) {
                    Image(
                        painterResource(id = R.drawable.ic_star_filled),
                        contentDescription = "Filled star"
                    )
                } else {
                    Image(
                        painterResource(id = R.drawable.ic_star_outlined),
                        contentDescription = "Empty star"
                    )
                }
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
                        modifier = Modifier.padding(6.dp)
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
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
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
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
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

        if (bookedBoatDetails.package_name != "") {
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
        if (bookedBoatDetails.product.isNotEmpty()) {
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

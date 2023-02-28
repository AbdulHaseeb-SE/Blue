package com.ah.studio.blueapp.ui.screens.home.subScreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.screens.home.HomeViewModel
import com.ah.studio.blueapp.ui.screens.home.domain.dto.PaymentMethod
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails.BoatDetails
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails.Package
import com.ah.studio.blueapp.ui.screens.home.domain.dto.booking.BoatBookingBody
import com.ah.studio.blueapp.ui.screens.home.domain.dto.cart.CartItems
import com.ah.studio.blueapp.ui.screens.home.domain.dto.delete.DeleteCartResponse
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.BookingDetailsManager
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
fun PaymentScreen(
    onPayAndConfirmClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    viewModel: HomeViewModel = getKoin().get()
) {
    val bookingDetailsManager = BookingDetailsManager(LocalContext.current)

    var cartList: List<CartItems>? by remember { mutableStateOf(null) }
    val boatCategory = bookingDetailsManager.retrieveDetails("boat_category")
    val destinationID = bookingDetailsManager.retrieveDetails("destination_id")
    val end = bookingDetailsManager.retrieveDetails("end")
    val boatTotal = bookingDetailsManager.retrieveDetails("boat_total")
    val start = bookingDetailsManager.retrieveDetails("start")
    val packageId = bookingDetailsManager.retrieveDetails("package_id")
    val boatID = bookingDetailsManager.retrieveDetails("boat_id")
    val dateSelected = bookingDetailsManager.retrieveDetails("dateSelected")
    val boatGrandTotal = boatTotal.toString()
    var grandTotal = 0
    var isLoading by remember { mutableStateOf(true) }
    var isBookingLoading by remember { mutableStateOf(false) }
    var boatDetails: BoatDetails? by remember { mutableStateOf(null) }
    var responseStatus by remember { mutableStateOf(0) }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                boatID?.toInt()?.let { viewModel.getBoatDetailsResponse(boatId = it) }
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

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading = true
            viewModel.getCartListResponse()
            viewModel.cartListResponse.collectLatest { response ->
                if (response != null) {
                    cartList = response.data
                    isLoading = false
                }
            }
        }
    }

    /*SideEffect {
        Log.d("checkPay", responseStatus.toString())
        if (responseStatus == 200) {
            isLoading = false
            onPayAndConfirmClick()
        } else if (responseStatus != 0) {
            isLoading = false
        }
    }*/

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
                text = stringResource(R.string.payment),
                navigationIconContentDescription = "",
                actionIcons = {},
                onNavigationIconClick = {
                    onBackButtonClick()
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Box {
            if (boatDetails != null) {
                isLoading = false
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
                    LocationSection(boatDetails)
                    CaptainBoatNameSection(boatDetails!!)
                    TimeDateSection(start, end, dateSelected)
                    grandTotal = summarySection(
                        boatDetails!!,
                        packageId,
                        cartList
                    )
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
                    ) {
                        isLoading = true
                        if (!cartList.isNullOrEmpty()) {
                            clearCartList(cartList, viewModel)
                        }
                        CoroutineScope(Dispatchers.Main).launch {
                            isBookingLoading = true
                            boatID?.toInt()?.let { boatId ->
                                boatTotal?.let { boatTotal ->
                                    end?.let { end ->
                                        start?.let { start ->
                                            packageId?.let { packageId ->
                                                if (destinationID != null) {
                                                    try {
                                                        viewModel.getBoatBookingResponse(
                                                            BoatBookingBody(
                                                                boat_category = boatCategory.toString(),
                                                                boat_grand_total = boatGrandTotal,
                                                                boat_id = boatId,
                                                                boat_total = boatTotal,
                                                                coupon_amount = "",
                                                                coupon_id = "0",
                                                                destination_id = destinationID,
                                                                end = end,
                                                                grand_total = grandTotal.toString(),
                                                                order_ref = "0",
                                                                package_id = packageId,
                                                                start = start,
                                                                total = grandTotal.toString(),
                                                                product = null
                                                            )
                                                        )
                                                        viewModel.boatBookingResponse.collectLatest { response ->
                                                            if (response != null) {
                                                                if (response.status == 200) {
                                                                    isBookingLoading = false
                                                                    snackbar =
                                                                        "Your Boat Booked Successfully!!"
                                                                    showSnackBar = true
                                                                    onPayAndConfirmClick()
                                                                } else {
                                                                    snackbar = response.message
                                                                    showSnackBar = true
                                                                    isBookingLoading = false
                                                                }
                                                            }
                                                        }
                                                    } catch (e: Exception) {
                                                        Log.d("Check", "booking exception = $e")
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (isLoading) {
                CircularProgressBar()
            }
            if (isBookingLoading) {
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

@Composable
fun CaptainBoatNameSection(boatDetails: BoatDetails) {
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
                        painter = painterResource(id = R.drawable.ic_profile_filled),
                        contentDescription = stringResource(R.string.clock_icon),
                        modifier = Modifier.padding(8.dp)
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
                    text = "Salim Alsafi",
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
                    painter = coilImageLoadingAsync(imageUrl = boatDetails.featured_image),
                    contentDescription = stringResource(R.string.clock_icon),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = boatDetails.name,
                fontSize = 16.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 9.dp)
            )
        }
    }
}

@Composable
fun TimeDateSection(start: String?, end: String?, dateSelected: String?) {
    val inputTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val outputTimeFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val inputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
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
                .fillMaxWidth(0.6f)
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
                    start?.let { it ->
                        inputTimeFormat.parse(it)?.let { it1 ->
                            outputTimeFormat.format(it1)
                        }
                    }.toString()
                } to ${
                    end?.let { it ->
                        inputTimeFormat.parse(it)?.let { it1 ->
                            outputTimeFormat.format(it1)
                        }
                    }.toString()
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
                text = dateSelected?.let {
                    inputDateFormat.parse(it)?.let { date ->
                        outputDateFormat.format(date)
                    }
                }.toString(),
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
fun LocationSection(boatDetails: BoatDetails?) {
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
                text = stringResource(R.string.parking_address),
                fontSize = 17.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
            )
            boatDetails?.pickup_address?.let {
                Text(
                    text = it,
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
}

@Composable
fun summarySection(boatDetails: BoatDetails, packageId: String?, cartList: List<CartItems>?): Int {

    var totalSummaryAmount = 0

    var packageDetails: Package? = null
    boatDetails.packages.forEach {
        if (packageId != null && packageId != "") {
            if (it.id == packageId.toInt()) {
                packageDetails = it
                totalSummaryAmount += packageDetails!!.price.toDouble().toInt()
            }
        }
    }
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
                text = boatDetails.name,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey700
            )
            Text(
                text = "${boatDetails.starting_from} KWD",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Grey700
            )
            totalSummaryAmount += boatDetails.starting_from.toDouble().toInt()
        }

        if (packageId != "" && packageDetails != null) {
            Divider(
                thickness = 1.dp,
                color = Black19Percent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = packageDetails!!.name,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fontFamily,
                    color = Grey700
                )
                Text(
                    text = "${packageDetails!!.price} KWD",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                    color = Grey700
                )
            }
        }

        if (!cartList.isNullOrEmpty()) {
            cartList.forEach { item ->
                totalSummaryAmount += (item.product_price.toDouble().toInt() * item.qty)

                Divider(
                    thickness = 1.dp,
                    color = Black19Percent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 8.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.name,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fontFamily,
                        color = Grey700
                    )

                    Text(
                        text = "${item.product_price} KWD",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Grey700
                    )
                }
            }
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
                text = "$totalSummaryAmount.00 KWD",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = OxfordBlue900
            )
        }

    }

    return totalSummaryAmount
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

fun clearCartList(
    cartList: List<CartItems>?,
    viewModel: HomeViewModel
): MutableState<DeleteCartResponse?> {
    val responseStatus: MutableState<DeleteCartResponse?> = mutableStateOf(null)
    if (!cartList.isNullOrEmpty()) {
        cartList.forEach { cartItem ->
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.getDeleteCartResponse(cartItem.id)
                viewModel.deleteCartItemResponse.collectLatest { response ->
                    if (response != null) {
                        responseStatus.value = response
                    }
                    return@collectLatest
                }
            }
        }
    }

    return responseStatus
}
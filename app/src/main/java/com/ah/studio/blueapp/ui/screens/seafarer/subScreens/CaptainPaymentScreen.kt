package com.ah.studio.blueapp.ui.screens.seafarer.subScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.screens.home.domain.dto.PaymentMethod
import com.ah.studio.blueapp.ui.screens.seafarer.SeafarerViewModel
import com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerList.Seafarer
import com.ah.studio.blueapp.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaptainPaymentScreen(
    captainId: Int,
    category: String,
    onBackButtonClick: () -> Unit,
    viewModel: SeafarerViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    var seafarerListResponse: List<Seafarer>? by remember {
        mutableStateOf(null)
    }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getSeafarerListResponse(
                page = 1,
                category = category
            )
            viewModel.seafarerListResponse.collectLatest { response ->
                if (response != null) {
                    seafarerListResponse = response.data
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
                    onBackButtonClick()
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Box {
            if (seafarerListResponse != null) {
                isLoading = false
                if (seafarerListResponse!!.isNotEmpty()) {
                    seafarerListResponse!!.forEach { seafarer ->
                        if (seafarer.id == captainId) {
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
                                SummarySection(seafarer)
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
            }
            if (isLoading) {
                CircularProgressBar()
            }
        }
    }
}

@Composable
fun SummarySection(seafarer: Seafarer) {
    BlueRoundedCornerShape(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 22.dp, top = 20.dp)
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
                    end = 10.dp,
                    bottom = 17.dp
                )
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Captain Unlock Charges",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey700
            )
            Text(
                text = "${seafarer.amount} KWD",
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
                .padding(bottom = 12.dp)
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
                text = "${seafarer.amount} KWD",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = OxfordBlue900
            )
        }
    }
}

@Composable
fun CouponSection() {
    Box(
        modifier = Modifier
            .padding(
                top = 50.dp,
                bottom = 45.dp
            ),
        contentAlignment = Alignment.CenterEnd
    ) {
        CustomTextField(
            label = stringResource(R.string.discount),
            placeholder = stringResource(R.string.coupon_code),
            value = {}
        )
        Button(
            width = 112.dp,
            height = 50.dp,
            text = stringResource(R.string.apply),
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
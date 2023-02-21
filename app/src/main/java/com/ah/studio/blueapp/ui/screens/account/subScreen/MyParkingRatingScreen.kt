package com.ah.studio.blueapp.ui.screens.account.subScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.screens.myBooking.CaptainBoatNameSection
import com.ah.studio.blueapp.ui.screens.myBooking.LocationSection
import com.ah.studio.blueapp.ui.screens.myBooking.SummarySection
import com.ah.studio.blueapp.ui.screens.myBooking.TimeDateSection
import com.ah.studio.blueapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyParkingRatingScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = stringResource(R.string.my_parkings),
                navigationIconContentDescription = "",
                actionIcons = {},
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
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
            LocationSection()
            CaptainBoatNameSection()
            TimeDateSection()
            SummarySection()

            Button(
                width = 0.dp,
                height = 50.dp,
                text = stringResource(R.string.rate_now),
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


@Preview
@Composable
fun PreviewMyParkingRatingScreen() {
    MyParkingRatingScreen()
}
package com.ah.studio.blueapp.ui.screens.account.subScreen


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BookedItem
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookingScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                navigationIconContentDescription = "",
                text = stringResource(id = R.string.my_bookings),
                elevation = 0.dp,
                actionIcons = { },
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        containerColor = Color.White,
    ) { paddingValues ->
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

            BookedItem(
                itemName = "Catamaran Boats",
                itemDescription = "",
                itemImage = painterResource(id = R.drawable.boat),
                price = "4.500 KWD",
                bookingId = "Booking id: 123456789",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 28.dp)
            )

            BookedItem(
                itemName = "Catamaran Boats",
                itemDescription = "",
                itemImage = painterResource(id = R.drawable.boat),
                price = "4.500 KWD",
                bookingId = "Booking id: 123456789",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 28.dp)
            )

            BookedItem(
                itemName = "Catamaran Boats",
                itemDescription = "",
                itemImage = painterResource(id = R.drawable.boat),
                price = "4.500 KWD",
                bookingId = "Booking id: 123456789",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 28.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewMyBookingScreen(){
    MyBookingScreen()
}
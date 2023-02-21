package com.ah.studio.blueapp.ui.screens.myBooking


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.ah.studio.blueapp.ui.component.CircularProgressBar
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.myBooking.domain.dto.BookingList
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBookingScreen(
    viewModel: BoatBookingViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    var boatBookingList: List<BookingList> by remember {
        mutableStateOf(emptyList())
    }
    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getBoatBookingListResponse()
            viewModel.boatBookingListResponse.collectLatest { list ->
                if (list != null) {
                    boatBookingList = list.data
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
                text = stringResource(id = R.string.my_bookings),
                actionIcons = { },
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

                if (boatBookingList.isNotEmpty()) {
                    LazyColumn {
                        itemsIndexed(
                            boatBookingList
                        ) { index, item ->
                            isLoading = false
                            BookedItem(
                                itemName = item.name,
                                itemDescription = "",
                                itemImage = coilImageLoadingAsync(imageUrl = item.image),
                                price = "${item.grand_total} KWD",
                                bookingId = "Booking id: ${if (item.booking_id_string!= null) item.booking_id_string else index.toString() }",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 28.dp)
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


@Preview
@Composable
fun PreviewMyBookingScreen() {
    MyBookingScreen()
}
package com.ah.studio.blueapp.ui.screens.home.subScreens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.Button
import com.ah.studio.blueapp.ui.component.DestinationListComponent
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.home.HomeViewModel
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails.BoatDetails
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails.DestinationAddress
import com.ah.studio.blueapp.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@Composable
fun DestinationScreen(
    boatId: Int,
    selectedDestinationId: (Int) -> Unit,
    viewModel: HomeViewModel = getKoin().get()
) {
    val selectedItemColor by remember { mutableStateOf(SeaBlue400) }
    val unSelectedItemColor by remember { mutableStateOf(SeaBlue08Percent) }
    var selectedIndex by remember { mutableStateOf(-1) }
    var isLoading by remember { mutableStateOf(true) }
    var boatDetails: BoatDetails? by remember { mutableStateOf(null) }
    var destinationList: List<DestinationAddress>? by remember { mutableStateOf(null) }
    var selectedDestination: Int? by remember { mutableStateOf(null) }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var snackbar by remember { mutableStateOf("") }
    var showSnackBar by remember { mutableStateOf(false) }


    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewModel.getBoatDetailsResponse(boatId = boatId)
                viewModel.boatDetailsResponse.collectLatest { response ->
                    response?.data?.forEach {
                        boatDetails = it
                        destinationList = boatDetails?.destination_address
                        if (destinationList.isNullOrEmpty()) {
                            showSnackBar = true
                            snackbar = "No Destination available yet !!"
                            isLoading = false
                        }
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
                text = stringResource(R.string.destination),
                navigationIconContentDescription = "",
                actionIcons = {},
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = PaddingDouble,
                        end = PaddingDouble,
                        top = it.calculateTopPadding()
                    )
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                if (!destinationList.isNullOrEmpty()) {
                    isLoading = false
                    LazyColumn {
                        itemsIndexed(
                            destinationList!!
                        ) { index, item ->
                            DestinationListComponent(
                                destinationName = item.destination_address,
                                destinationTime = "Min ${item.destination_hrs} Hrs",
                                cost = item.price,
                                textColor = if (index != selectedIndex) Grey700 else Color.White,
                                costTextColor = if (index == selectedIndex) Color.White else OxfordBlue900,
                                containerColor = if (index == selectedIndex) selectedItemColor else unSelectedItemColor
                            ) {
                                selectedIndex = index
                                selectedDestination = item.id
                            }
                        }
                    }
                    Button(
                        width = 0.dp,
                        height = 50.dp,
                        text = stringResource(id = R.string.select_destination),
                        backgroundColor = SeaBlue400,
                        shape = Shapes.medium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(46.dp)
                    ) {
                        if (selectedDestination != null) {
                            selectedDestination?.let { destinationID ->
                                selectedDestinationId(
                                    destinationID
                                )
                            }
                        } else {
                            snackbar = "No Destination Selected !!"
                            showSnackBar = true
                        }
                    }
                }

            }
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center),
                        color = SeaBlue400,
                        strokeWidth = 4.dp
                    )
                }
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

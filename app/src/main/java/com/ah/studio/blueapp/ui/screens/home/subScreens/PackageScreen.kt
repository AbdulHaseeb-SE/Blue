package com.ah.studio.blueapp.ui.screens.home.subScreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails.BoatDetails
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.BookingDetailsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackageScreen(
    boatId: Int,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    viewModel: HomeViewModel = getKoin().get()
) {
    val bookingDetailsManager = BookingDetailsManager(LocalContext.current)
    var isLoading by remember { mutableStateOf(true) }
    var showDialog by remember { mutableStateOf(false) }
    var boatDetails: BoatDetails? by remember { mutableStateOf(null) }
    var packageId: Int? by remember {
        mutableStateOf(null)
    }
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

    var selectedIndex by remember {
        mutableStateOf(-1)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = stringResource(R.string.packages),
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
                onNavigationIconClick = {
                        onBackButtonClick()
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = PaddingDouble,
                    end = PaddingDouble,
                    top = it.calculateTopPadding()
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (!boatDetails?.packages.isNullOrEmpty()) {
                isLoading = false
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = it.calculateTopPadding()
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    itemsIndexed(
                        boatDetails?.packages!!
                    ) { index, item ->
                        PackageComponent(
                            packageName = item.name,
                            featuresDescription = item.features,
                            cost = item.price + " KWD",
                            buttonText = if (index == selectedIndex)
                                stringResource(id = R.string.added) else stringResource(R.string.add),
                            buttonBackgroundColor = if (index == selectedIndex) SeaBlue08Percent else SeaBlue400
                        ) {
                            packageId = item.id
                            selectedIndex = index
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = PaddingHalf),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    width = 0.dp,
                    height = 50.dp,
                    text = stringResource(id = R.string.next),
                    backgroundColor = SeaBlue400,
                    shape = Shapes.medium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(end = PaddingHalf)
                ) {
                    if (packageId != null) {
                        bookingDetailsManager.saveSingleDetails("package_id", packageId.toString())
                        onNextClick()
                    }else {
                        bookingDetailsManager.saveSingleDetails("package_id", "")
                        onNextClick()
                    }
                }

                BlueRoundedCornerShape(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {
                            showDialog = true
                        }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.skip),
                            fontSize = 17.sp,
                            color = OxfordBlue900,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = PaddingHalf)
                        )
                    }
                }
            }
            if (isLoading) {
                CircularProgressBar()
            }
            if (showDialog) {
                ConfirmationDialog(
                    showDialog = showDialog,
                    title = stringResource(R.string.confirmation),
                    message = stringResource(R.string.skip_confirmation_dialog_message),
                    yesButtonColor = Color.Red,
                    onConfirm = {
                        showDialog = false
                        bookingDetailsManager.saveSingleDetails("package_id", "")
                        onSkipClick()
                    },
                    onCancel = {
                        showDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun PackageComponent(
    packageName: String,
    featuresDescription: String,
    cost: String,
    buttonText: String,
    buttonBackgroundColor: Color,
    onClick: () -> Unit
) {
    BlueRoundedCornerShape(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = PaddingDouble)
    ) {
        Text(
            text = packageName,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(
                    top = 17.dp,
                    bottom = 18.dp,
                    start = PaddingDouble,
                    end = PaddingDouble
                )
                .fillMaxWidth()
        )

        Divider(
            thickness = 1.dp,
            color = Black30Percent,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.features),
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = fontFamily,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    bottom = 6.dp,
                    start = PaddingDouble,
                    end = PaddingDouble
                )
                .fillMaxWidth()
        )
        Text(
            text = featuresDescription,
            fontSize = 17.sp,
            fontWeight = FontWeight.Light,
            fontFamily = fontFamily,
            color = Color.Black,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(
                    top = 0.dp,
                    bottom = PaddingDouble,
                    start = PaddingDouble,
                    end = PaddingDouble
                )
                .fillMaxWidth()
        )
        Text(
            text = cost,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = fontFamily,
            color = OxfordBlue900,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(
                    top = 2.dp,
                    bottom = 28.dp,
                    start = PaddingDouble,
                    end = PaddingDouble
                )
                .fillMaxWidth()
        )

        BlueRoundedCornerShape(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    bottom = 21.dp,
                    start = 28.dp,
                    end = 28.dp
                )
                .clickable { onClick() },
            containerColor = buttonBackgroundColor,
        ) {
            Text(
                text = buttonText,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = fontFamily,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp)
            )
        }
    }
}

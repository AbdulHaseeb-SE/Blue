package com.ah.studio.blueapp.ui.screens.home

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.LocationComponent
import com.ah.studio.blueapp.ui.component.LocationPermissionTextProvider
import com.ah.studio.blueapp.ui.component.PermissionDialog
import com.ah.studio.blueapp.ui.component.VehicleCategoryCard
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.Category
import com.ah.studio.blueapp.ui.screens.main.viewModel.BottomNavViewModel
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.ApiConstants.STORAGE_URL
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin


@Composable
fun HomeScreen(
    onClick: (String) -> Unit,
    viewModel: HomeViewModel = getKoin().get(),
    mainViewModel: BottomNavViewModel = getKoin().get()
) {
    val dialogQueue = mainViewModel.visiblePermissionDialogQueue
    val permissionsToRequest = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    val context = LocalContext.current
    val locationPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { perms ->
            permissionsToRequest.forEach { permission ->
                mainViewModel.onPermissionResult(
                    permission = permission,
                    isGranted = perms[permission] == true
                )
            }
        }
    )
    val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(LocalContext.current)
    var isLoading by remember { mutableStateOf(true) }
    var categoryList: List<Category>? by remember {
        mutableStateOf(listOf())
    }
    var location by remember {
        mutableStateOf("Fetch Location")
    }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewModel.getBoatCategoryResponse()
                viewModel.categoryList.collectLatest { list ->
                    if (!list.isNullOrEmpty()) {
                        categoryList = list
                    }
                }
            } catch (e: Exception) {
                Log.e("Exception", "${e.message.toString()} ${e.cause} ${e.localizedMessage}")
            }

        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                LocationComponent(
                    painterResource(id = R.drawable.ic_location),
                    location,
                    4.dp,
                    PaddingLarge,
                    PaddingDouble,
                    locationTextFontWeight = FontWeight.Normal,
                    modifier = Modifier.clickable {
                        if (checkPermission(context)) {
                            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                                val result = task.result
                                location = if (result != null)
                                    result.longitude.toString() + result.latitude.toString()
                                else
                                    "Unable to Fetch Your Location!"
                            }
                        } else {
                            locationPermissionResultLauncher.launch(permissionsToRequest)
                            if (checkPermission(context)) {
                                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                                    val result = task.result
                                    location = if (result != null)
                                        result.longitude.toString() + result.latitude.toString()
                                    else
                                        "Unable to Fetch Your Location!"
                                }
                            }
                        }
                    }
                )
                Text(
                    text = stringResource(R.string.select_category),
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = PaddingLarge,
                            start = PaddingDouble,
                            end = PaddingDouble
                        )
                )

                if (categoryList != null) {
                    isLoading = false
                    CategoryComponent(
                        categoryList = categoryList!!,
                        onClick = {
                            onClick(it)
                        }
                    )
                }
            }
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White.copy(alpha = 0f))
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
        dialogQueue
            .reversed()
            .forEach { permission ->
                PermissionDialog(
                    permissionTextProvider = when (permission) {
                        Manifest.permission.ACCESS_FINE_LOCATION -> {
                            LocationPermissionTextProvider()
                        }
                        Manifest.permission.ACCESS_COARSE_LOCATION -> {
                            LocationPermissionTextProvider()
                        }
                        else -> return@forEach
                    },
                    isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                        Activity(),
                        permission
                    ),
                    onDismiss = mainViewModel::dismissDialog,
                    onOkClick = {
                        mainViewModel.dismissDialog()
                        locationPermissionResultLauncher.launch(
                            arrayOf(permission)
                        )
                    },
                    onGoToAppSettingsClick = Activity()::openAppSettings
                )
            }
    }
}

@Composable
fun CategoryComponent(
    categoryList: List<Category>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = PaddingDouble,
                start = PaddingDouble,
                end = PaddingDouble
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        itemsIndexed(categoryList) { _, item ->

            VehicleCategoryCard(
                modifier = modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(top = 14.dp),
                backgroundColor = SeaBlue100,
                shape = Shapes.medium,
                elevation = 0.dp,
                imageUrl = STORAGE_URL + item.image,
                vehicleName = item.name,
                textColor = SeaBlue400,
                textSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                nameStartPadding = PaddingLarge,
                nameTopPadding = 20.dp,
                imageTopPadding = 0.dp
            ) { name ->
                onClick(name)
            }
        }
    }
}

private fun checkPermission(
    context: Context
): Boolean {
    return ActivityCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_LOCATION_SOURCE_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}
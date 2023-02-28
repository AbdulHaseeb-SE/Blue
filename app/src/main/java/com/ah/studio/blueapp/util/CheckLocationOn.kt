package com.ah.studio.blueapp.util

import android.content.Context
import android.location.LocationManager
import androidx.compose.runtime.Composable

@Composable
fun checkLocationStatus(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
        LocationManager.NETWORK_PROVIDER
    )
}
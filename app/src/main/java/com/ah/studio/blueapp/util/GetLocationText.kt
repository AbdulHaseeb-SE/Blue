package com.ah.studio.blueapp.util

import android.content.Context
import android.location.Geocoder
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun locationName(longitude: Double, latitude: Double, context: Context): String {
    var location by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true) {
        CoroutineScope(Dispatchers.Main)
            .launch {
                val geocoder = Geocoder(context, Locale.getDefault())
                val result = async(Dispatchers.Default) {
                    geocoder.getFromLocation(latitude, longitude, 1)
                }
                if (result.await()?.isNotEmpty() == true) {
                    location = result.await()!!.first().locality
                }
            }
    }

    return location
}
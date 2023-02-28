package com.ah.studio.blueapp.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.util.ApiConstants.STORAGE_URL

@Composable
fun coilImageLoadingAsync(imageUrl: String): Painter = rememberAsyncImagePainter(
    model = ImageRequest.Builder(LocalContext.current)
        .data(STORAGE_URL + imageUrl)
        .crossfade(500)
        .error(R.drawable.ic_no_image)
        .build(),
    contentScale = ContentScale.Crop
)
package com.ah.studio.blueapp.ui.component

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.screens.home.domain.dto.gallery.Gallery
import com.ah.studio.blueapp.util.coilImageLoadingAsync


@Composable
fun ImageViewer(images: List<Gallery>, clickedImageIndex: Int, onBackPressed: () -> Unit) {
    var imageIndex by remember { mutableStateOf(clickedImageIndex) }

    Log.d("CheckImageViewer", "image index = $imageIndex")
    Log.d("CheckImageViewer", "list size = ${images.size}")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            coilImageLoadingAsync(imageUrl = images[imageIndex].image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 100.dp, horizontal = 50.dp)
                .fillMaxSize()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (imageIndex > 0) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "Previous Image",
                    modifier = Modifier
                        .rotate(180f)
                        .size(40.dp)
                        .clickable {
                            if (imageIndex > 0) {
                                imageIndex -= 1
                            }
                        },
                    colorFilter = ColorFilter.tint(Color.White)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                )
            }
            if (imageIndex < images.size - 1) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "Previous Image",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            if (imageIndex < images.size - 1) {
                                imageIndex += 1
                            }
                        },
                    colorFilter = ColorFilter.tint(Color.White)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }
    }

    BackHandler {
        onBackPressed()
    }
}
package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ah.studio.blueapp.R

@Composable
fun VehicleCategoryCard(
    backgroundColor: Color,
    shape: Shape,
    elevation: Dp,
    imageUrl: String,
    vehicleName: String,
    textColor: Color,
    textSize: TextUnit,
    fontWeight: FontWeight,
    fontFamily: FontFamily,
    modifier: Modifier = Modifier,
    imageTopPadding: Dp = 0.dp,
    imageBottomPadding: Dp = 0.dp,
    imageStartPadding: Dp = 0.dp,
    imageEndPadding: Dp = 0.dp,
    nameTopPadding: Dp = 0.dp,
    nameBottomPadding: Dp = 0.dp,
    nameStartPadding: Dp = 0.dp,
    nameEndPadding: Dp = 0.dp,
    onCardClick: (String) -> Unit
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(500)
            .error(R.drawable.yacht)
            .build(),
        contentScale = ContentScale.Crop
    )
    Card(
        modifier = modifier
            .clickable { onCardClick(vehicleName) },
        backgroundColor = backgroundColor,
        shape = shape,
        elevation = elevation
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(
                        top = imageTopPadding,
                        bottom = imageBottomPadding,
                        start = imageStartPadding,
                        end = imageEndPadding
                    )
                    .fillMaxWidth()
            )
            Text(
                text = vehicleName,
                fontSize = textSize,
                fontFamily = fontFamily,
                color = textColor,
                fontWeight = fontWeight,
                modifier = Modifier.padding(
                    top = nameTopPadding,
                    start = nameStartPadding,
                    bottom = nameBottomPadding,
                    end = nameEndPadding
                )
            )
        }
    }
}

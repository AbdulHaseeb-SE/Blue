package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.StarColor
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun StarRatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    totalStarCount: Int = 5,
    starsColor: Color = StarColor,
) {

    val filledStars = floor(rating).toInt()
    val unfilledStars = (totalStarCount - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {

        repeat(filledStars) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_filled),
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier.size(24.dp)
            )
        }

        if (halfStar) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_filled),
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier.size(24.dp)
            )
        }

        repeat(unfilledStars) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_outlined),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
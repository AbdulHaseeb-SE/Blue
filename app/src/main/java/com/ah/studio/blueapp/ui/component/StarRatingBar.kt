package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }
    }
}
package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.*

@Composable
fun RestaurantCartItem(
    itemName: String,
    itemDescription: String,
    itemImage: Painter,
    price: String,
    timeRequired: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        RoundedCornerImageView(
            painter = itemImage,
            shape = Shapes.medium,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(110.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = itemName,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    maxLines = 1
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_edit),
                    contentDescription = stringResource(
                        R.string.edit_item
                    ),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(end = 2.dp)
                        .size(30.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_timer),
                    contentDescription = stringResource(
                        R.string.timer
                    ),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.padding(end = 2.dp)
                )

                Text(
                    text = timeRequired,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    maxLines = 2,
                    modifier = Modifier.padding(start = 3.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = price,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = OxfordBlue900,
                    maxLines = 1,
                    modifier = Modifier.padding(vertical = 6.dp)
                )

                AddSubtractItem(
                    buttonTextSize = 26.sp,
                    paddingAroundItemText = 6.dp,
                    horizontalArrangement = Arrangement.End
                )
            }
        }
    }
}

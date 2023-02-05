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
import com.ah.studio.blueapp.ui.theme.OxfordBlue900
import com.ah.studio.blueapp.ui.theme.Shapes
import com.ah.studio.blueapp.ui.theme.fontFamily

@Composable
fun BookedItem(
    itemName: String,
    itemDescription: String,
    itemImage: Painter,
    price: String,
    bookingId: String,
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
                .height(100.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 9.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = itemName,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Color.Black,
                maxLines = 1
            )

            Text(
                text = bookingId,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Color.Black,
                maxLines = 1,
                modifier = Modifier.padding(vertical = 10.dp)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_tick),
                        contentDescription = stringResource(
                            R.string.tick
                        ),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.padding(end = 5.dp)
                    )

                    Text(
                        text = stringResource(R.string.booked),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fontFamily,
                        color = Color.Black,
                        maxLines = 1
                    )
                }

                Text(
                    text = price,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = OxfordBlue900,
                    maxLines = 1
                )
            }
        }
    }
}

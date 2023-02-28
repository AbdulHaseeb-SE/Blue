package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.ui.theme.fontFamily

@Composable
fun AddSubtractItem(
    buttonTextSize: TextUnit = 20.sp,
    paddingAroundItemText: Dp = 10.dp,
    buttonSize: Dp = 34.dp,
    qty: Int = 1,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceEvenly,
    onItemCountChanged: (Int) -> Unit
) {
    var itemCount by remember {
        mutableStateOf(qty)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BlueRoundedCornerShape(
            modifier = Modifier
                .width(buttonSize)
                .height(buttonSize)
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 6.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "-",
                    fontSize = buttonTextSize,
                    letterSpacing = 0.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    modifier = Modifier
                        .clickable {
                            if (itemCount > 0) itemCount -= 1
                            onItemCountChanged(itemCount)
                        }
                )
            }
        }

        Text(
            text = String.format("%02d", itemCount),
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = fontFamily,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = paddingAroundItemText, vertical = 1.dp)
        )

        BlueRoundedCornerShape(
            modifier = Modifier
                .width(buttonSize)
                .height(buttonSize)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "+",
                    fontSize = buttonTextSize,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    modifier = Modifier.clickable {
                        itemCount += 1
                        onItemCountChanged(itemCount)
                    }
                )
            }
        }


    }
}

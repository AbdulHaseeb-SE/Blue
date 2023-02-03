package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.ui.screens.home.domain.dto.PaymentMethod
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.*

@Composable
fun PaymentMethod(
    modifier: Modifier = Modifier,
    methodList: List<PaymentMethod> = listOf()
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    val selectedColor by remember {
        mutableStateOf(SeaBlue400)
    }
    val unSelectedColor by remember {
        mutableStateOf(Grey600)
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        itemsIndexed(methodList) { index, item ->
            BlueRoundedCornerShape(
                modifier = Modifier
                    .width(100.dp)
                    .height(70.dp)
                    .clickable {
                        selectedIndex = index
                    },
                containerColor = if (index == selectedIndex) Color.White else Grey200,
                borderColor = if (index == selectedIndex) selectedColor else unSelectedColor
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = item.image,
                        contentDescription = item.name/*,
                        colorFilter = (if (index != selectedIndex) unSelectedColor else null)?.let { it1 ->
                            ColorFilter.tint(
                                it1
                            )
                        }*/
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewPaymentMethods() {
    PaymentMethod(
        methodList = listOf(
            PaymentMethod(
                name = "Visa Card",
                image = painterResource(id = R.drawable.ic_visacard)
            ),
            PaymentMethod(
                name = "Master Card",
                image = painterResource(id = R.drawable.ic_mastercard)
            ),
            PaymentMethod(
                name = "KNET Card",
                image = painterResource(id = R.drawable.ic_knet)
            )
        )
    )
}
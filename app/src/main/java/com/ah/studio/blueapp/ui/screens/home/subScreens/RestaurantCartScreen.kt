package com.ah.studio.blueapp.ui.screens.home.subScreens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BlueRoundedCornerShape
import com.ah.studio.blueapp.ui.component.Button
import com.ah.studio.blueapp.ui.component.RestaurantCartItem
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.home.domain.dto.RestaurantMenuItem
import com.ah.studio.blueapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantCartScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                navigationIconContentDescription = "",
                text = stringResource(id = R.string.cart),
                elevation = 0.dp,
                actionIcons = { },
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        containerColor = Color.White,
    ) { paddingValues ->
        var queryText by remember {
            mutableStateOf("")
        }
        val cartItemList = cartItemList()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    start = 10.dp,
                    end = 10.dp
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                LazyColumn {
                    itemsIndexed(cartItemList) { _, item ->
                        RestaurantCartItem(
                            itemName = item.itemName,
                            itemDescription = item.itemDescription,
                            itemImage = item.image,
                            price = item.price,
                            timeRequired = item.timeRequired,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                        )
                        Divider(
                            thickness = 1.dp,
                            color = Black19Percent,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(
                                    top = 10.dp,
                                    bottom = 23.dp
                                )
                        )
                    }
                }

                Text(
                    text = stringResource(R.string.queries),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.Black,
                )

                BlueRoundedCornerShape(
                    containerColor = Color.White,
                    borderColor = OxfordBlue900Percent37,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .wrapContentHeight()
                        .animateContentSize()
                ) {
                    TextField(
                        value = queryText,
                        onValueChange = { newText ->
                            queryText = newText
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.option_to_write),
                                fontSize = 15.sp,
                                fontFamily = fontFamily
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            cursorColor = Color.Black,
                            placeholderColor = Color.Black,
                            textColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = fontFamily
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Default
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp)
                    )
                }
            }

            Button(
                width = 0.dp,
                height = 50.dp,
                text = stringResource(id = R.string.proceed_to_checkout),
                backgroundColor = SeaBlue400,
                shape = Shapes.medium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(46.dp)
            ) {}


        }
    }
}


@Composable
fun cartItemList(): List<RestaurantMenuItem> = listOf(
    RestaurantMenuItem(
        itemName = "Chicken Burger",
        itemDescription = "Lorem Ipsum is simply dummy text of the printing and",
        price = "4.500 KWD",
        image = painterResource(id = R.drawable.ic_chicken_burger)
    ),

    RestaurantMenuItem(
        itemName = "Chicken Pizza",
        itemDescription = "Lorem Ipsum is simply dummy text of the printing and",
        price = "4.500 KWD",
        image = painterResource(id = R.drawable.ic_pizza)
    )
)


@Preview
@Composable
fun PreviewRestaurantCartScreen() {
    RestaurantCartScreen()
}
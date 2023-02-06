package com.ah.studio.blueapp.ui.screens.home.subScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.RestaurantMenuItem
import com.ah.studio.blueapp.ui.component.SearchBar
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.home.domain.dto.RestaurantMenuItem
import com.ah.studio.blueapp.ui.theme.Grey600
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.PaddingHalf
import com.ah.studio.blueapp.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantMenuScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                navigationIconContentDescription = "",
                text = stringResource(id = R.string.restaurants),
                actionIcons = {

                    Image(
                        painter = painterResource(id = R.drawable.ic_cart),
                        contentDescription = stringResource(
                            R.string.button_notification
                        ),
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .size(24.dp),
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                },
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
    ) {
        var text by remember {
            mutableStateOf("")
        }
        val menuItemList = menuItemList()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    start = PaddingDouble,
                    end = PaddingDouble
                ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "Jamawar Crowne Plaza Kuwait",
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Color.Black,
                maxLines = 1,
                modifier = Modifier.padding(top = PaddingHalf)
            )

            Text(
                text = "India, Asian, Vegetarian Friendly",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = Grey600,
                maxLines = 1,
                modifier = Modifier.padding(bottom = 19.dp)
            )

            SearchBar(
                text = text,
                onTextChange = { changedText ->
                    text = changedText
                },
                onSearchClicked = {},
                modifier = Modifier.padding(bottom = 20.dp)
            )

            MenuItemSection(menuItemList)
        }
    }
}

@Composable
fun MenuItemSection(menuItemList: List<RestaurantMenuItem>) {
    LazyColumn {
        itemsIndexed(
            menuItemList
        ) { _, item ->
            RestaurantMenuItem(
                itemName = item.itemName,
                itemDescription = item.itemDescription,
                itemImage = item.image,
                price = item.price,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .height(110.dp)
            )
        }
    }
}

@Composable
fun menuItemList(): List<RestaurantMenuItem> = listOf(
    RestaurantMenuItem(
        itemName = "Chicken Burger",
        itemDescription = "Lorem Ipsum is simply dummy text of the printing and",
        price = "4.500 KWD",
        image = painterResource(id = R.drawable.ic_chicken_burger)
    ),
    RestaurantMenuItem(
        itemName = "Mix Kabab",
        itemDescription = "Lorem Ipsum is simply dummy text of the printing and",
        price = "4.5 KWD",
        image = painterResource(id = R.drawable.ic_kabab)
    ),
    RestaurantMenuItem(
        itemName = "Pan Cakes",
        itemDescription = "Lorem Ipsum is simply dummy text of the printing and",
        price = "4.500 KWD",
        image = painterResource(id = R.drawable.ic_pan_cake)
    ),
    RestaurantMenuItem(
        itemName = "Chicken Pizza",
        itemDescription = "Lorem Ipsum is simply dummy text of the printing and",
        price = "4.500 KWD",
        image = painterResource(id = R.drawable.ic_pizza)
    ),
    RestaurantMenuItem(
        itemName = "Mix Kabab",
        itemDescription = "Lorem Ipsum is simply dummy text of the printing and",
        price = "4.5 KWD",
        image = painterResource(id = R.drawable.ic_kabab)
    ),
    RestaurantMenuItem(
        itemName = "Pan Cakes",
        itemDescription = "Lorem Ipsum is simply dummy text of the printing and",
        price = "4.500 KWD",
        image = painterResource(id = R.drawable.ic_pan_cake)
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
fun PreviewRestaurantOfferingScreen() {
    RestaurantMenuScreen()
}
package com.ah.studio.blueapp.ui.screens.home.subScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BlueRoundedCornerShape
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.home.domain.dto.AccessoriesCategory
import com.ah.studio.blueapp.ui.screens.home.domain.dto.Restaurant
import com.ah.studio.blueapp.ui.screens.home.domain.dto.RestaurantCategory
import com.ah.studio.blueapp.ui.theme.*
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                navigationIconContentDescription = stringResource(id = R.string.back_button),
                text = stringResource(id = R.string.restaurants),
                elevation = 0.dp,
                actionIcons = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = stringResource(
                            R.string.button_notification
                        ),
                        modifier = Modifier
                            .padding(end = PaddingDouble)
                            .size(24.dp)
                    )
                },
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val selectedItemColor by remember {
                mutableStateOf(SeaBlue400)
            }
            val unSelectedItemColor by remember {
                mutableStateOf(Grey500)
            }
            var selectedIndex by remember {
                mutableStateOf(0)
            }
            var selectedSubCategory by remember {
                mutableStateOf("Restaurants")
            }
            var restaurantCategorySelectedIndex by remember {
                mutableStateOf(-1)
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                itemsIndexed(
                    listOf(
                        "Restaurants",
                        "Accessories"
                    )
                ) { index, item ->
                    TabSection(
                        tabTitle = item,
                        dividerColor = if (index == selectedIndex) selectedItemColor else unSelectedItemColor
                    ) {
                        selectedIndex = index
                    }
                }
            }

            SubCategorySection(selectedIndex) { category, indexSelected ->
                selectedSubCategory = category
                restaurantCategorySelectedIndex = indexSelected
            }

            when (selectedSubCategory) {
                "Restaurants" -> {
                    RestaurantSection(selectedCategoryIndex = restaurantCategorySelectedIndex)
                }
                "Accessories" -> {}
            }
        }
    }
}

@Composable
fun RestaurantCard(
    name: String,
    description: String,
    image: Painter,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 34.dp,
                start = PaddingDouble,
                end = PaddingDouble
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = image,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .padding(bottom = 10.dp)
        )
        Text(
            text = name,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = fontFamily,
            color = Color.Black,
            maxLines = 1
        )
        Text(
            text = description,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = fontFamily,
            color = Grey600,
            maxLines = 1
        )
    }
}

@Composable
fun SubCategorySection(
    selectedIndex: Int,
    onClick: (category: String, selectedIndex: Int) -> Unit,
) {

    var selectedCategoryIndex by remember {
        mutableStateOf(0)
    }
    val unSelectedContainerColor by remember {
        mutableStateOf(SeaBlue08Percent)
    }
    val selectedContainerColor by remember {
        mutableStateOf(SeaBlue400)
    }
    val selectedTextColor by remember {
        mutableStateOf(Color.White)
    }
    val unSelectedTextColor by remember {
        mutableStateOf(Color.Black)
    }
    val selectedContentColor by remember {
        mutableStateOf(Color.White)
    }
    val unSelectedContentColor by remember {
        mutableStateOf(Blue900)
    }


    val restaurantCategoryList: List<RestaurantCategory> = restaurantCategoryList()
    val accessoryCategoryList: List<AccessoriesCategory> = accessoriesCategoryList()

    when (selectedIndex) {
        0 -> {
            LazyRow(
                modifier = Modifier
                    .padding(
                        top = 35.dp,
                        start = 12.dp,
                        end = 12.dp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                selectedCategoryIndex = 0
                itemsIndexed(
                    restaurantCategoryList
                ) { index, item ->
                    onClick(
                        "Restaurants",
                        selectedCategoryIndex
                    )
                    CategoryCard(
                        title = item.categoryName,
                        icon = item.categoryIcon,
                        containerColor = if (index == selectedCategoryIndex) selectedContainerColor else unSelectedContainerColor,
                        contentColor = if (index == selectedCategoryIndex) selectedContentColor else unSelectedContentColor,
                        textColor = if (index == selectedCategoryIndex) selectedTextColor else unSelectedTextColor
                    ) {
                        selectedCategoryIndex = index
                        onClick(
                            "Restaurants",
                            selectedCategoryIndex
                        )
                    }
                }
            }
        }
        1 -> {
            LazyRow(
                modifier = Modifier
                    .padding(
                        top = 35.dp,
                        start = 12.dp,
                        end = 12.dp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                selectedCategoryIndex = 0
                itemsIndexed(
                    accessoryCategoryList
                ) { index, item ->
                    onClick(
                        "Accessories",
                        0
                    )
                    CategoryCard(
                        title = item.accessoryName,
                        icon = item.accessoryIcon,
                        containerColor = if (index == selectedCategoryIndex) selectedContainerColor else unSelectedContainerColor,
                        contentColor = if (index == selectedCategoryIndex) selectedContentColor else unSelectedContentColor,
                        textColor = if (index == selectedCategoryIndex) selectedTextColor else unSelectedTextColor
                    ) {
                        selectedCategoryIndex = index
                        onClick(
                            "Accessories",
                            selectedCategoryIndex
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RestaurantSection(selectedCategoryIndex: Int) {

    val restaurantList = listOf<Restaurant>(
        Restaurant(
            "Jamawar Crowne Plaza Kuwait",
            "Indian, Asian, Vegetarian Friendly",
            painterResource(id = R.drawable.restaurant)
        ),
        Restaurant(
            "Mais Alghanim",
            "Lebanese, Grill, Middle Eastern",
            painterResource(id = R.drawable.ig_restaurant_interior)
        ),
        Restaurant(
            "Freij Sweileh",
            "Middle Eastern, Vegetarian Friendly, Halal",
            painterResource(id = R.drawable.restaurant)
        ),
        Restaurant(
            "Jamawar Crowne Plaza Kuwait",
            "Indian, Asian, Vegetarian Friendly",
            painterResource(id = R.drawable.restaurant)
        )
    )


    when (selectedCategoryIndex) {
        0 -> {
            LazyColumn {
                itemsIndexed(
                    restaurantList
                ) { _, item ->
                    RestaurantCard(
                        name = item.name,
                        description = item.description,
                        image = item.image
                    )
                }
            }
        }
        1 -> {
            LazyColumn {
                itemsIndexed(
                    restaurantList.shuffled(Random())
                ) { _, item ->
                    RestaurantCard(
                        name = item.name,
                        description = item.description,
                        image = item.image
                    )
                }
            }
        }
    }
}

@Composable
fun restaurantCategoryList(): List<RestaurantCategory> {
    return listOf(
        RestaurantCategory(
            categoryName = "Food",
            categoryIcon = painterResource(id = R.drawable.ic_burger)
        ),
        RestaurantCategory(
            categoryName = "Beverages",
            categoryIcon = painterResource(id = R.drawable.ic_beverages)
        ),
        RestaurantCategory(
            categoryName = "Tea & Hot Drink",
            categoryIcon = painterResource(id = R.drawable.ic_tea)
        ),
        RestaurantCategory(
            categoryName = "Caterings",
            categoryIcon = painterResource(id = R.drawable.ic_caterings)
        )
    )
}

@Composable
fun accessoriesCategoryList(): List<AccessoriesCategory> {
    return listOf(
        AccessoriesCategory(
            accessoryName = "Vest",
            accessoryIcon = painterResource(id = R.drawable.ic_vest)
        ),
        AccessoriesCategory(
            accessoryName = "Goggles",
            accessoryIcon = painterResource(id = R.drawable.ic_goggles)
        ),
        AccessoriesCategory(
            accessoryName = "Cap",
            accessoryIcon = painterResource(id = R.drawable.ic_cap)
        ),
        AccessoriesCategory(
            accessoryName = "Wetsuits",
            accessoryIcon = painterResource(id = R.drawable.ic_wetsuits)
        )
    )
}

@Composable
fun CategoryCard(
    title: String,
    icon: Painter,
    textColor: Color,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    BlueRoundedCornerShape(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .padding(horizontal = 4.dp)
            .clickable { onClick() },
        containerColor = containerColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 9.dp
                ),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = icon,
                contentDescription = "",
                colorFilter = ColorFilter.tint(contentColor)
            )
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = textColor,
                maxLines = 2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(51.dp)
                    .padding(bottom = 15.dp)
            )
        }
    }
}

@Composable
fun TabSection(
    tabTitle: String,
    dividerColor: Color,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .wrapContentHeight()
            .clickable {
                onClick(tabTitle)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = tabTitle,
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = fontFamily,
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )
        Divider(
            thickness = 1.dp,
            color = dividerColor,
            modifier = Modifier
                .requiredWidthIn(min = 180.dp, max = 220.dp)
                .requiredWidth(width = 180.dp)
        )
    }
}

@Preview
@Composable
fun PreviewRestaurantScreen() {
    RestaurantScreen()
}

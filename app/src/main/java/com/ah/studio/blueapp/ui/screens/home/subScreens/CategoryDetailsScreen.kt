package com.ah.studio.blueapp.ui.screens.home.subScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BoatListCard
import com.ah.studio.blueapp.ui.component.HorizontalTabs
import com.ah.studio.blueapp.ui.component.SearchBar
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.home.domain.dto.Boat
import com.ah.studio.blueapp.ui.theme.Grey600
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.PaddingLarge
import com.ah.studio.blueapp.ui.theme.SeaBlue400

@Composable
fun CategoryDetailsScreen(navHostController: NavHostController) {

    val receivedCategoryText by remember {
        mutableStateOf("Boats")
    }

    val boatList: MutableList<Boat> = mutableListOf(
        Boat(
            boatImage = painterResource(id = R.drawable.ic_boat),
            boatName = "Catamaran Boats",
            location = stringResource(id = R.string.al_jahra_kuwait),
            price = "Starting from 100.000 KWD"
        ),
        Boat(
            boatImage = painterResource(id = R.drawable.ic_boat),
            boatName = "Catamaran Boats",
            location = stringResource(id = R.string.al_jahra_kuwait),
            price = "Starting from 100.000 KWD"
        ),
        Boat(
            boatImage = painterResource(id = R.drawable.boat),
            boatName = "Catamaran Boats",
            location = stringResource(id = R.string.al_jahra_kuwait),
            price = "Starting from 100.000 KWD"
        ),
        Boat(
            boatImage = painterResource(id = R.drawable.ic_boat),
            boatName = "Catamaran Boats",
            location = stringResource(id = R.string.al_jahra_kuwait),
            price = "Starting from 100.000 KWD"
        ),
    )

    val categoryList = listOf<String>(
        "Luxury Boat",
        "Sailing Boat",
        "Sports Boat",
    )

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = receivedCategoryText,
                elevation = 0.dp,
                navigationIconContentDescription = stringResource(R.string.back_button),
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
                onNavigationIconClick = {})
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = it.calculateTopPadding(),
                    horizontal = PaddingDouble
                )
                .verticalScroll(rememberScrollState())
        ) {
            CategoryTabs(
                categoryList = categoryList,
                modifier = Modifier.fillMaxWidth()
            )
            BoatListSection(boatList)
        }
    }
}

@Composable
fun CategoryTabs(
    categoryList: List<String>,
    modifier: Modifier = Modifier
) {
    HorizontalTabs(
        categoryList = categoryList,
        backgroundColor = Color.White,
        selectedContentColor = Color.Black,
        unselectedContentColor = Grey600,
        indicatorActiveColor = SeaBlue400
    ) {

    }
}

@Composable
fun BoatListSection(
    boatList: MutableList<Boat>,
    modifier: Modifier = Modifier
) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = PaddingLarge),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            text = text,
            onTextChange = {
                text = it
            },
            onSearchClicked = {}
        )
        boatList.forEach { boat ->
            BoatListCard(
                boatImage = boat.boatImage,
                boatName = boat.boatName,
                boatLocation = boat.location,
                boatPrice = boat.price,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(135.dp)
                    .padding(top = 21.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewDetails() {
    CategoryDetailsScreen(navHostController = rememberNavController())
}
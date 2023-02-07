package com.ah.studio.blueapp.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.LocationComponent
import com.ah.studio.blueapp.ui.component.VehicleCategoryCard
import com.ah.studio.blueapp.ui.theme.*


@Composable
fun HomeScreen(onClick: (String) -> Unit) {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        MainContainer(it, onClick= {categoryName ->
            onClick(categoryName)
        })
    }

}

@Composable
fun MainContainer(
    paddingValues: PaddingValues,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        LocationComponent(
            painterResource(id = R.drawable.ic_location),
            stringResource(R.string.al_jahra_kuwait),
            PaddingHalf,
            PaddingLarge,
            PaddingDouble
        )
        CategoryComponent(
            onClick = {
                onClick(it)
            }
        )
    }
}

@Composable
fun CategoryComponent(onClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 30.dp,
                start = PaddingDouble,
                end = PaddingDouble
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.select_category),
            fontSize = 17.sp,
            fontFamily = fontFamily,
            color = Color.Black,
        )

        Text(
            text = stringResource(R.string.lorem_ipsum),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily,
            color = Color.Black,
            modifier = modifier.padding(bottom = PaddingDouble)
        )
        CategoryCards() { categoryName ->
            onClick(categoryName)
        }
    }
}

@Composable
fun CategoryCards(modifier: Modifier = Modifier, onClick: (String) -> Unit) {
    VehicleCategoryCard(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(top = 14.dp),
        backgroundColor = SeaBlue100,
        shape = Shapes.medium,
        elevation = 0.dp,
        vehicleImage = painterResource(id = R.drawable.yacht),
        vehicleName = stringResource(R.string.yacht),
        textColor = Azure400,
        textSize = 32.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = fontFamily,
        nameStartPadding = PaddingLarge,
        nameTopPadding = 20.dp,
        imageTopPadding = 40.dp
    ) { name ->
        onClick(name)
    }

    VehicleCategoryCard(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(top = 14.dp),
        backgroundColor = SeaBlue100,
        shape = Shapes.medium,
        elevation = 0.dp,
        vehicleImage = painterResource(id = R.drawable.boat),
        vehicleName = stringResource(R.string.boat),
        textColor = Azure400,
        textSize = 32.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = fontFamily,
        nameStartPadding = PaddingLarge,
        nameTopPadding = 20.dp,
        imageTopPadding = 40.dp
    ) { name ->
        onClick(name)
    }
}


@Preview
@Composable
fun PreviewHome() {
    HomeScreen(){}
}

package com.ah.studio.blueapp.ui.screens.home.subScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.ah.studio.blueapp.ui.component.Button
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.home.domain.dto.Destination
import com.ah.studio.blueapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationScreen() {

    val destinationList: List<Destination> = listOf(
        Destination(
            "Destination 1",
            "Min 4 Hrs",
            "100.000 KWD"
        ),
        Destination(
            "Destination 2",
            "Min 4 Hrs",
            "100.000 KWD"
        ),
        Destination(
            "Destination 3",
            "Min 4 Hrs",
            "100.000 KWD"
        ),
        Destination(
            "Destination 4",
            "Min 4 Hrs",
            "100.000 KWD"
        ),
        Destination(
            "Destination 5",
            "Min 4 Hrs",
            "100.000 KWD"
        )
    )

    var destinationName by remember {
        mutableStateOf("Destination Name")
    }
    var reachingTime by remember {
        mutableStateOf("Min 4 Hrs")
    }
    var cost by remember {
        mutableStateOf("100.000 KWD")
    }
    val selectedItemColor by remember {
        mutableStateOf(SeaBlue400)
    }
    val unSelectedItemColor by remember {
        mutableStateOf(SeaBlue08Percent)
    }
    var selectedIndex by remember {
        mutableStateOf(-1)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = stringResource(R.string.destination),
                elevation = 0.dp,
                navigationIconContentDescription = "",
                actionIcons = {},
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = PaddingDouble,
                    end = PaddingDouble,
                    top = it.calculateTopPadding()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            LazyColumn {
                itemsIndexed(
                    destinationList
                ) { index, item ->
                    destinationName = item.destinationName
                    reachingTime = item.reachingTime
                    cost = item.cost
                    DestinationListComponent(
                        destinationName,
                        reachingTime,
                        cost,
                        textColor = if (index != selectedIndex) Grey700 else Color.White,
                        costTextColor = if (index == selectedIndex) Color.White else OxfordBlue900,
                        containerColor = if (index == selectedIndex) selectedItemColor else unSelectedItemColor
                    ) {
                        selectedIndex = index
                    }
                }
            }

            Button(
                width = 0.dp,
                height = 50.dp,
                text = stringResource(id = R.string.select_destination),
                backgroundColor = SeaBlue400,
                shape = Shapes.medium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(46.dp)
            ) {}
        }
    }
}

@Composable
fun DestinationListComponent(
    destinationName: String,
    destinationTime: String,
    cost: String,
    containerColor: Color = SeaBlue08Percent,
    textColor: Color,
    costTextColor: Color,
    onClick: () -> Unit
) {
    BlueRoundedCornerShape(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = PaddingHalf)
            .clickable { onClick() },
        containerColor = containerColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = destinationName,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    color = textColor,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    modifier = Modifier
                        .width(170.dp)
                        .padding(top = 13.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(bottom = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_clock_outlined),
                        contentDescription = stringResource(R.string.clock_icon),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp)
                    )
                    Text(
                        text = destinationTime,
                        fontSize = 14.sp,
                        fontFamily = fontFamily,
                        color = textColor,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(horizontal = 6.dp)
                    )
                }
            }

            Text(
                text = cost,
                fontSize = 20.sp,
                fontFamily = fontFamily,
                color = costTextColor,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}


@Preview
@Composable
fun PreviewDestinationScreen() {
    DestinationScreen()
}
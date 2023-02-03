package com.ah.studio.blueapp.ui.screens.home.subScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.ui.screens.home.domain.dto.Package
import kotlin.math.cos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PackageScreen() {

    val packageList = listOf(
        Package(
            "Birthday Package",
            stringResource(id = R.string.lorem_ipsum),
            cost = "200.000 KWD"
        ),
        Package(
            "Summer Package",
            stringResource(id = R.string.lorem_ipsum),
            cost = "50.000 KWD"
        ),
        Package(
            "Winter Package",
            stringResource(id = R.string.lorem_ipsum),
            cost = "300.000 KWD"
        ),
        Package(
            "Birthday Package",
            stringResource(id = R.string.lorem_ipsum),
            cost = "500.000 KWD"
        )
    )

    var packageName by remember {
        mutableStateOf("Destination Name")
    }
    var featuresDescription by remember {
        mutableStateOf("Min 4 Hrs")
    }
    var cost by remember {
        mutableStateOf("100.000 KWD")
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
                text = stringResource(R.string.packages),
                elevation = 0.dp,
                navigationIconContentDescription = "",
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
                    packageList
                ) { index, item ->
                    PackageComponent(
                        packageName = item.packageName,
                        featuresDescription = item.featureDescription,
                        cost = item.cost,
                        buttonText = if (index == selectedIndex)
                            stringResource(id = R.string.added) else stringResource(R.string.add),
                        buttonBackgroundColor = if (index == selectedIndex) SeaBlue08Percent else SeaBlue400
                    ) { selectedIndex = index }
                }
            }
        }
    }
}

@Composable
fun PackageComponent(
    packageName: String,
    featuresDescription: String,
    cost: String,
    buttonText: String,
    buttonBackgroundColor: Color,
    onClick: () -> Unit
) {
    BlueRoundedCornerShape(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = PaddingDouble)
    ) {
        Text(
            text = packageName,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = fontFamily,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(
                    top = 17.dp,
                    bottom = 18.dp,
                    start = PaddingDouble,
                    end = PaddingDouble
                )
                .fillMaxWidth()
        )

        Divider(
            thickness = 1.dp,
            color = Black30Percent,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.features),
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = fontFamily,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    bottom = 6.dp,
                    start = PaddingDouble,
                    end = PaddingDouble
                )
                .fillMaxWidth()
        )
        Text(
            text = featuresDescription,
            fontSize = 17.sp,
            fontWeight = FontWeight.Light,
            fontFamily = fontFamily,
            color = Color.Black,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(
                    top = 0.dp,
                    bottom = PaddingDouble,
                    start = PaddingDouble,
                    end = PaddingDouble
                )
                .fillMaxWidth()
        )
        Text(
            text = cost,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = fontFamily,
            color = OxfordBlue900,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(
                    top = 2.dp,
                    bottom = 28.dp,
                    start = PaddingDouble,
                    end = PaddingDouble
                )
                .fillMaxWidth()
        )

        BlueRoundedCornerShape(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    bottom = 21.dp,
                    start = 28.dp,
                    end = 28.dp
                )
                .clickable { onClick() },
            containerColor = buttonBackgroundColor,
        ) {
            Text(
                text = buttonText,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = fontFamily,
                fontSize = 17.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 14.dp)
            )
        }
    }
}


@Preview
@Composable
fun PreviewPackageScreen() {
    PackageScreen()
}

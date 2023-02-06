package com.ah.studio.blueapp.ui.screens.seafarer

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BlueRoundedCornerShape
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeafarerScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = null,
                text = "",
                navigationIconContentDescription = "",
                actionIcons = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = stringResource(
                            R.string.button_add
                        )
                    )
                },
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        var selectedTabIndex by remember {
            mutableStateOf(0)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = PaddingDouble,
                    end = PaddingDouble,
                    top = it.calculateTopPadding()
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            TabBarSection() { index ->
                selectedTabIndex = index
            }

            when (selectedTabIndex) {
                0 -> {
                    CaptainDetailCard(true)
                    CaptainDetailCard(false)
                    CaptainDetailCard(false)
                }
                1 -> {}
                2 -> {}
                3 -> {}
            }
        }
    }
}

@Composable
fun CaptainDetailCard(
    isPayed : Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .wrapContentHeight()
    ) {

        BlueRoundedCornerShape(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 19.dp,
                        bottom = 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Salim Alsafi",
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.55f)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_contact_us),
                    contentDescription = ""
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_chat),
                    contentDescription = ""
                )
            }

            Divider(
                thickness = (0.5).dp,
                color = Black30Percent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = PaddingDouble),
                horizontalAlignment = Alignment.Start
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 5.dp,
                            bottom = 9.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Text(
                            text = stringResource(R.string.experience_text),
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Grey700,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "5 Years",
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Text(
                            text = stringResource(R.string.age_text),
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Grey700,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "35 Years",
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 9.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(R.string.nationality_text),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Grey700,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Indian",
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(R.string.boat_experience),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Grey700,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Sailing Yacht, Motor Yacht",
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 26.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(R.string.spoken_languages_text),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Grey700,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "English , Arabic, Hindi",
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        if (!isPayed) PayToUnlock()
    }
}

@Composable
fun PayToUnlock() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_blurred_background),
            contentDescription = "",
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 18.dp
                )
                .background(White2Percent),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_lock_outlined),
                contentDescription = "",
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(
                text = stringResource(R.string.pay_to_unlock),
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Red700,
                fontSize = 17.sp
            )
        }
    }
}

@Composable
fun TabBarSection(
    onClick: (Int) -> Unit
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 9.dp,
                bottom = 29.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        itemsIndexed(
            listOf(
                "Captains",
                "Helper",
                "Technician",
                "Mate"
            )
        ) { index, item ->
            Text(
                text = item,
                fontFamily = fontFamily,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (selectedIndex == index) Color.Black else Black50Percent,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(end = 19.dp)
                    .clickable {
                        selectedIndex = index
                        onClick(index)
                    }
            )
        }
    }
}

@Preview
@Composable
fun PreviewSeafarer() {
    SeafarerScreen(navHostController = rememberNavController())
}
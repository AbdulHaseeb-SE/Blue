package com.ah.studio.blueapp.ui.screens.home.subScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.Button
import com.ah.studio.blueapp.ui.component.RoundedCornerImageView
import com.ah.studio.blueapp.ui.component.StarRatingBar
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.theme.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoatDetailsScreen(navHostController: NavHostController) {

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = "",
                elevation = 0.dp,
                navigationIconContentDescription = stringResource(id = R.string.back_button),
                actionIcons = {},
                onNavigationIconClick = {})
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            RoundedCornerImageView(
                painter = painterResource(id = R.drawable.ic_boat_bottom_rounded),
                shape = RoundedCornerShape(
                    topEnd = 0.dp,
                    topStart = 0.dp,
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp)
            )

            BoatNameDetailsSection()

            BoatGallerySection()

            FacilitiesSection()

            AddressSection()

            BottomSection()

        }
    }

}

@Composable
fun BottomSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 40.dp, start = PaddingDouble,  end = PaddingDouble),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            width = 283.dp,
            height = 50.dp,
            text = stringResource(R.string.book_now),
            backgroundColor = SeaBlue400,
            shape = Shapes.medium
        ) {

        }
    }
}

@Composable
fun AddressSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 23.dp, horizontal = PaddingDouble),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_circled_location),
                contentDescription = stringResource(R.string.location_icon),
            )

            Column(
                modifier = Modifier.padding(horizontal = 9.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.pick_up_address),
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "Marina Mall, Salmiya",
                    fontSize = 14.sp,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 22.dp,
                    horizontal = 50.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 9.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.captain),
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun FacilitiesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Facilities",
            fontSize = 17.sp,
            fontFamily = fontFamily,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingDouble)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 21.dp, start = PaddingHalf, end = PaddingDouble),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyRow(content = {
                itemsIndexed(
                    listOf(
                        R.drawable.ic_wifi,
                        R.drawable.ic_food,
                        R.drawable.ic_snack,
                        R.drawable.ic_ac
                    )
                ) { _, image ->
                    RoundedCornerImageView(
                        painter = painterResource(id = image),
                        shape = Shapes.medium,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
            })
        }
    }
}

@Composable
fun BoatGallerySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp)
    ) {
        Text(
            text = "View all",
            fontSize = 14.sp,
            fontFamily = fontFamily,
            color = Grey500,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingDouble)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, end = PaddingDouble),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyRow(content = {
                itemsIndexed(
                    listOf(
                        R.drawable.boat,
                        R.drawable.ic_boat,
                        R.drawable.boat
                    )
                ) { _, image ->
                    RoundedCornerImageView(
                        painter = painterResource(id = image),
                        shape = Shapes.medium,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(124.dp)
                            .height(112.dp)
                            .padding(start = 16.dp)
                    )
                }
            })
        }

        Text(
            text = stringResource(R.string.dummy_description),
            fontSize = 17.sp,
            fontFamily = fontFamily,
            color = Color.Black,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = PaddingDouble,
                    end = PaddingDouble,
                    top = 70.dp
                )
        )
    }
}

@Composable
fun BoatNameDetailsSection() {
    Column(
        modifier = Modifier.padding(
            horizontal = PaddingDouble,
            vertical = PaddingHalf
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.wrapContentWidth()
            ) {
                Text(
                    text = "Catamaran Boats",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                    color = Color.Black
                )

                StarRatingBar(rating = 4f)
            }
            Column(
                modifier = Modifier.wrapContentWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Starting from\n 100.000 KWD",
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    color = OxfordBlue900
                )
            }
        }
    }
}


@Preview
@Composable
fun Preview() {
    BoatDetailsScreen(navHostController = rememberNavController())
}
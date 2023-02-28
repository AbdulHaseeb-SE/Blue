package com.ah.studio.blueapp.ui.screens.home.subScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.ah.studio.blueapp.ui.component.LocationComponent
import com.ah.studio.blueapp.ui.component.StarRatingBar
import com.ah.studio.blueapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        containerColor = Color.White
    ) { paddingValues ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = paddingValues.calculateTopPadding(),
                    horizontal = PaddingDouble
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            LocationComponent(
                painter = painterResource(id = R.drawable.ic_location),
                locationText = stringResource(R.string.al_jahra_kuwait),
                locationStartPadding = PaddingHalf,
                rowTopPadding = PaddingLarge,
                iconPaddingStart = PaddingDouble
            )

            ItemSection()
            ReviewOnItemSection()

            Text(
                text = stringResource(R.string.customer_reviews),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 17.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(top = 27.dp)
                    .fillMaxWidth()
            )

            CustomerReviewSection()
            CustomerReviewSection()
        }
    }
}

@Composable
fun CustomerReviewSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, bottom = 9.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Mohammed Al Kandari",
            fontFamily = fontFamily,
            fontWeight = FontWeight(600),
            color = Color.Black,
            fontSize = 17.sp,
            textAlign = TextAlign.Start,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_star_outlined),
                contentDescription = "",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            )
            Text(
                text = "4.9",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(start = 7.dp)
            )
        }
    }

    Text(
        text = stringResource(id = R.string.lorem_ipsum),
        fontFamily = fontFamily,
        fontWeight = FontWeight.Light,
        color = Color.Black,
        fontSize = 17.sp,
        textAlign = TextAlign.Justify,
        modifier = Modifier
            .fillMaxWidth()
    )

    Divider(
        thickness = 1.dp,
        color = Black19Percent,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 21.dp)
    )

}

@Composable
fun ReviewOnItemSection() {
    BlueRoundedCornerShape(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 19.dp,
                    bottom = 25.dp,
                    start = 13.dp,
                    end = 13.dp
                ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "4.6/5",
                fontFamily = fontFamily,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
            Text(
                text = "Based on 120 reviews",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 6.dp)
            )
            StarRatingBar(
                rating = 4.0,
                modifier = Modifier.background(Color.Transparent)
            )
        }
    }
}

@Composable
fun ItemSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 46.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Chicken Burger",
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 17.sp,
        )

        Text(
            text = "4.500 KWD",
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 17.sp,
        )
    }
    Text(
        text = "Lorem Ipsum is simply dummy text of the printing and  ",
        fontFamily = fontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 17.sp,
        maxLines = 2,
        color = Color.Black,
        modifier = Modifier
            .padding(top = 6.dp)
            .fillMaxWidth(0.7f)
    )
}


@Preview
@Composable
fun PreviewReview(){
    ReviewScreen()
}
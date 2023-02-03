package com.ah.studio.blueapp.ui.screens.myParking.subScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DoneScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = "",
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
                onNavigationIconClick = { /*TODO*/ })
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Image(
                painter = painterResource(id = R.drawable.boat_illustration),
                contentDescription = stringResource(
                    R.string.boat_Image
                ),
                modifier = Modifier.padding(horizontal = 30.dp, vertical = 69.dp)
            )

            BlueRoundedCornerShape(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = stringResource(R.string.slot_booked),
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = PaddingDouble,
                            vertical = 18.dp
                        )
                )
                Divider(
                    thickness = 1.dp,
                    color = Black30Percent,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = stringResource(R.string.parking_details),
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = PaddingDouble,
                            end = PaddingDouble,
                            top = 10.dp
                        )
                )
                Text(
                    text = stringResource(R.string.lorem_ipsum),
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = PaddingDouble,
                            top = 8.dp
                        )
                )
                Text(
                    text = stringResource(R.string.done),
                    fontSize = 30.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    lineHeight = 38.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = PaddingDouble,
                            vertical = 18.dp
                        )
                )

            }

            Button(
                width = 0.dp,
                height = 50.dp,
                text = stringResource(R.string.home),
                backgroundColor = SeaBlue400,
                shape = Shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 30.dp,
                        end = 30.dp,
                        bottom = 50.dp,
                        top = 28.dp
                    ),
                fontWeight = FontWeight.SemiBold
            ) {}
        }
    }
}


@Preview
@Composable
fun PreviewDoneScreen() {
    DoneScreen()
}
package com.ah.studio.blueapp.ui.screens.myParking.subScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.ah.studio.blueapp.ui.component.Button
import com.ah.studio.blueapp.ui.theme.SeaBlue400
import com.ah.studio.blueapp.ui.theme.Shapes
import com.ah.studio.blueapp.ui.theme.fontFamily

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoBoatScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = 26.dp,
                    vertical = 130.dp
                )
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.boat_illustration),
                contentDescription = stringResource(
                    R.string.boat_Image
                ),
                modifier = Modifier.padding(horizontal = 29.dp)
            )
            Text(
                text = stringResource(R.string.no_boat_message),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 73.dp)
            )

            Button(
                width = 283.dp,
                height = 50.dp,
                text = stringResource(R.string.add_boats),
                backgroundColor = SeaBlue400,
                shape = Shapes.medium,
                fontWeight = FontWeight.Bold,
            ) {}
        }
    }
}

@Preview
@Composable
fun PreviewNoBoatScreen() {
    NoBoatScreen()
}
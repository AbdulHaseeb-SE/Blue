package com.ah.studio.blueapp.ui.screens.myParking.subScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
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
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.CustomDropDown
import com.ah.studio.blueapp.ui.component.CustomTextField
import com.ah.studio.blueapp.ui.component.RoundedCornerImageView
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddBoatScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 74.dp, start = PaddingDouble, end = PaddingDouble)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            CustomTextField(
                label = stringResource(R.string.boat_name),
                placeholder = stringResource(id = R.string.boat_name),
                labelFontSize = 13.sp,
                textFontSize = 17.sp
            ) {}
            CustomTextField(
                label = stringResource(R.string.height),
                placeholder = stringResource(id = R.string.height),
                labelFontSize = 13.sp,
                textFontSize = 17.sp
            ) {}
            CustomTextField(
                label = stringResource(R.string.width),
                placeholder = stringResource(id = R.string.width),
                labelFontSize = 13.sp,
                textFontSize = 17.sp
            ) {}

            CustomDropDown(
                listItems = arrayOf("Boat Type", "Yacht", "Boats"),
                labelFontSize = 13.sp,
                textFontSize = 17.sp
            ) { }

            Text(
                text = stringResource(R.string.add_images),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = Color.Black,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            )


            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                RoundedCornerImageView(
                    painter = painterResource(id = R.drawable.ic_no_image),
                    shape = RoundedCornerShape(17.dp),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(88.dp)
                        .height(88.dp)
                        .padding(end = 10.dp)
                )
                RoundedCornerImageView(
                    painter = painterResource(id = R.drawable.ic_no_image),
                    shape = RoundedCornerShape(17.dp),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(88.dp)
                        .height(88.dp)
                        .padding(end = 10.dp)
                )
                RoundedCornerImageView(
                    painter = painterResource(id = R.drawable.ic_no_image),
                    shape = RoundedCornerShape(17.dp),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(88.dp)
                        .height(88.dp)
                        .padding(end = 10.dp)
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewAddBoatScreen() {
    AddBoatScreen()
}
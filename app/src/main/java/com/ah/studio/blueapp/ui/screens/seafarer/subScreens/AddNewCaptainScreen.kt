package com.ah.studio.blueapp.ui.screens.seafarer.subScreens

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
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewCaptainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = null,
                text = stringResource(R.string.add_new),
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
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            CustomTextField(
                label = stringResource(id = R.string.name),
                placeholder = "Mohammed",
                labelFontSize = 13.sp,
                textFontSize = 17.sp,
                modifier = Modifier.padding(top = 26.dp)
            ) {}
            CustomTextField(
                label = stringResource(id = R.string.mobile_number),
                placeholder = "+965",
                labelFontSize = 13.sp,
                textFontSize = 17.sp,
            ) {}
            CustomTextField(
                label = stringResource(id = R.string.age),
                placeholder = "35",
                labelFontSize = 13.sp,
                textFontSize = 17.sp,
            ) {}
            CustomDropDown(
                listItems = arrayOf("Indian", "Pakistan"),
                labelText = stringResource(R.string.nationality),
                labelFontSize = 13.sp,
                textFontSize = 17.sp,
                selectedText = {}
            )
            CustomDropDown(
                listItems = arrayOf("English, Arabic", "Hindi"),
                labelText = stringResource(R.string.spoken_languages),
                labelFontSize = 13.sp,
                textFontSize = 17.sp,
                selectedText = {}
            )
            CustomTextField(
                label = stringResource(id = R.string.experience),
                placeholder = "3 Years",
                labelFontSize = 13.sp,
                textFontSize = 17.sp,
            ) {}

            CustomDropDown(
                listItems = arrayOf("Boat Experience", "Sailing Yacht", "Motor Yacht"),
                labelFontSize = 13.sp,
                textFontSize = 17.sp,
                selectedText = {}
            )

            CustomTextField(
                label = stringResource(id = R.string.license_number),
                placeholder = "M65656HJH",
                labelFontSize = 13.sp,
                textFontSize = 17.sp,
            ) {}


            Text(
                text = stringResource(R.string.add_license_images),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                color = Color.Black,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        bottom = 12.dp
                    )
                    .fillMaxWidth()
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
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
                        .width(98.dp)
                        .height(88.dp)
                        .padding(end = 10.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAddNewCaptain() {
    AddNewCaptainScreen()
}
package com.ah.studio.blueapp.ui.screens.account.subScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.theme.Grey800
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.fontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermAndConditionScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                navigationIconContentDescription = "",
                text = stringResource(id = R.string.terms_and_condition),
                actionIcons = {},
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.White,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding() + 24.dp,
                    start = 18.dp,
                    end = 18.dp
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = stringResource(R.string.term_and_services),
                fontWeight = FontWeight.Normal,
                fontSize = 36.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.aliquam_ornare),
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                fontFamily = fontFamily,
                color = Grey800,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.terms_and_conditions),
                fontWeight = FontWeight.Black,
                fontSize = 13.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                modifier = Modifier
                    .padding(
                        top = 34.dp,
                        bottom = 13.dp
                    )
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.aliquam_ornare),
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                fontFamily = fontFamily,
                color = Grey800,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.policies),
                fontWeight = FontWeight.Black,
                fontSize = 13.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                modifier = Modifier
                    .padding(
                        top = 34.dp,
                        bottom = 13.dp
                    )
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.aliquam_ornare),
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                fontFamily = fontFamily,
                color = Grey800,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.services),
                fontWeight = FontWeight.Black,
                fontSize = 13.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                modifier = Modifier
                    .padding(
                        top = 34.dp,
                        bottom = 13.dp
                    )
                    .fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.aliquam_ornare),
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                fontFamily = fontFamily,
                color = Grey800,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(
                        start = 3.dp,
                        end = 3.dp,
                        bottom = PaddingDouble
                    )
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun PreviewTermAndCondition() {
    TermAndConditionScreen()
}
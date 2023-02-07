package com.ah.studio.blueapp.ui.screens.account.subScreen


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.Button
import com.ah.studio.blueapp.ui.component.CustomTextField
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                navigationIconContentDescription = "",
                text = stringResource(id = R.string.change_password),
                actionIcons = { },
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        containerColor = Color.White,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding() + 20.dp,
                    start = PaddingHalf,
                    end = PaddingHalf
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            CustomTextField(
                label = stringResource(id = R.string.current_password),
                placeholder = stringResource(id = R.string.current_password),
                value = {}
            )

            CustomTextField(
                label = stringResource(id = R.string.new_password),
                placeholder = stringResource(id = R.string.new_password),
                value = {},
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
            )
            CustomTextField(
                label = stringResource(id = R.string.confirm_password),
                placeholder = stringResource(id = R.string.confirm_password),
                value = {},
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
            )

            Button(
                width = 0.dp,
                height = 50.dp,
                text = stringResource(id = R.string.submit),
                backgroundColor = SeaBlue400,
                shape = Shapes.medium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 46.dp, vertical = 88.dp)
            ) {}
        }
    }
}

@Preview
@Composable
fun PreviewChangePasswordScreen() {
    ChangePasswordScreen()
}
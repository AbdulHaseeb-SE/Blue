package com.ah.studio.blueapp.ui.screens.authentication

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.component.Button
import com.ah.studio.blueapp.ui.component.CustomTextField
import com.ah.studio.blueapp.ui.theme.*


@Composable
fun SignUpScreen(navHostController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(it)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_top_gradient),
                contentDescription = "",
                modifier = Modifier.padding(start = PaddingTripleLarge)
            )
            RegistrationForm(navHostController)
        }

    }
}

@Composable
fun RegistrationForm(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(PaddingDouble)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.register),
            color = OxfordBlue900,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(vertical = 58.dp)
        )
        CustomTextField(
            label = stringResource(R.string.first_name),
            placeholder = stringResource(R.string.first_name)
        ) {}
        CustomTextField(
            label = stringResource(R.string.last_name),
            placeholder = stringResource(R.string.last_name)
        ) {}
        CustomTextField(
            label = stringResource(R.string.phone_number),
            placeholder = stringResource(R.string.plus965),
            keyboardType = KeyboardType.Phone
        ) {}
        CustomTextField(
            label = stringResource(R.string.email),
            placeholder = stringResource(R.string.email),
            keyboardType = KeyboardType.Email
        ) {}
        CustomTextField(
            label = stringResource(R.string.password),
            placeholder = stringResource(R.string.password),
            keyboardType = KeyboardType.Password
        ) {}
        CustomTextField(
            label = stringResource(R.string.confirm_password),
            placeholder = stringResource(R.string.confirm_password),
            keyboardType = KeyboardType.Password
        ) {}
        CustomTextField(
            label = stringResource(R.string.civil_id),
            placeholder = stringResource(R.string.civil_id)
        ) {}

        Button(
            width = 280.dp,
            height = 50.dp,
            text = stringResource(id = R.string.register),
            backgroundColor = SeaBlue400,
            shape = Shapes.medium,
            modifier = modifier.padding(top = PaddingDouble)
        ) {}

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = PaddingLarge,
                    bottom = PaddingDouble
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.already_have_an_account),
                fontSize = 16.sp,
                color = Color.Black,
            )
            Text(
                text = stringResource(R.string.signIn),
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = modifier.clickable {
                    navHostController.navigate(ScreenController.SignIn.route)
                }
            )
        }
    }
}
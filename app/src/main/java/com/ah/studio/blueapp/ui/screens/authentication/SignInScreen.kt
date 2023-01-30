package com.ah.studio.blueapp.ui.screens.authentication

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.Graph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignInScreen(
    navHostController: NavHostController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                modifier = Modifier
                    .height(105.dp)
                    .align(Alignment.BottomCenter),
                painter = painterResource(id = R.drawable.bg_bottom_gradient),
                contentDescription = ""
            )
            Sign_In(navHostController)
        }
    }
}

@Composable
fun Sign_In(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.sign_in),
            color = OxfordBlue900,
            fontSize = 34.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(top = 58.dp, bottom = 48.dp)
        )

        Text(
            text = stringResource(R.string.hello_again),
            color = Color.Black,
            fontSize = 42.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = stringResource(R.string.welcome_back),
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = fontFamily,
            modifier = modifier
                .padding(top = 10.dp)
                .width(160.dp),
            textAlign = TextAlign.Center
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 38.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                label = stringResource(id = R.string.email),
                placeholder = stringResource(id = R.string.email)
            ) {}

            PasswordTextField(
                label = stringResource(id = R.string.password),
                placeholder = stringResource(id = R.string.password),
                keyboardType = KeyboardType.Password
            ) {}

            Text(
                text = stringResource(R.string.forgot_password),
                fontSize = 16.sp,
                textAlign = TextAlign.End,
                color = Color.Black,
                fontFamily = fontFamily,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 2.dp)
            )

            Button(
                width = 285.dp,
                height = 50.dp,
                text = stringResource(id = R.string.login),
                backgroundColor = SeaBlue400,
                shape = Shapes.medium,
                modifier = modifier.padding(top = PaddingLarge)
            ) {
                navHostController.navigate(Graph.Home)
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 30.dp,
                        start = 50.dp,
                        end = 50.dp
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalGradientLine(
                    width = 50.dp,
                    height = 1.dp,
                    colors = listOf(
                        Color.White,
                        OxfordBlue900
                    )
                )

                Text(
                    text = stringResource(R.string.or_continue_with),
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontFamily = fontFamily,
                    modifier = modifier
                        .padding(horizontal = PaddingSmall)
                )

                HorizontalGradientLine(
                    width = 50.dp,
                    height = 1.dp,
                    colors = listOf(
                        OxfordBlue900,
                        Color.White
                    )
                )
            }

            Row(
                modifier = modifier
                    .width(200.dp)
                    .padding(top = PaddingLarge),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageButton(
                    width = 70.dp,
                    height = 70.dp,
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = stringResource(R.string.sign_in_google_account)
                ) {}
                ImageButton(
                    width = 70.dp,
                    height = 70.dp,
                    painter = painterResource(id = R.drawable.ic_apple),
                    contentDescription = stringResource(R.string.sign_in_apple_account)
                ) {}
            }

            Button(
                width = 220.dp,
                height = 50.dp,
                text = stringResource(R.string.owns_a_boat_yacht),
                backgroundColor = SeaBlue400,
                shape = Shapes.medium,
                modifier = modifier.padding(top = 32.dp)
            ) {
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 28.dp,
                        bottom = PaddingDouble
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.dont_have_an_account),
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontFamily = fontFamily
                )
                Text(
                    text = stringResource(R.string.signUp),
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.clickable {
                        navHostController.navigate(ScreenController.SignUp.route)
                    }
                )
            }
        }

    }
}


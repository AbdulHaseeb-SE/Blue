package com.ah.studio.blueapp.ui.screens.account

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.SwitchTabButton
import com.ah.studio.blueapp.ui.component.TabButton
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.theme.Grey600
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                navigationIconContentDescription = "",
                text = stringResource(R.string.account),
                elevation = 0.dp,
                actionIcons = {},
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    start = PaddingDouble,
                    end = PaddingDouble
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            UserNameEmailSection()
            BookingButton()
            MyParkingButton()
            TermsAndConditionButton()
            ContactUsButton()
            RefundPolicyButton()
            PushNotificationButton()
            EditProfileButton()
            ChangePasswordButton()
            LogoutButton()

        }
    }
}

@Composable
fun LogoutButton() {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_logout_filled),
        tabText = stringResource(R.string.logout),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun ChangePasswordButton() {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_lock),
        tabText = stringResource(R.string.change_password),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun EditProfileButton() {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_profile_filled),
        tabText = stringResource(R.string.edit_profile),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun PushNotificationButton() {
    SwitchTabButton(
        tabIcon = painterResource(id = R.drawable.ic_notification_filled),
        tabText = stringResource(R.string.push_notification),
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun RefundPolicyButton() {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_refund_document),
        tabText = stringResource(R.string.refund_policy),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun ContactUsButton() {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_contact_us),
        tabText = stringResource(R.string.contact_us),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun TermsAndConditionButton() {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_document_filled),
        tabText = stringResource(R.string.terms_and_condition),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun MyParkingButton() {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_boat_filled),
        tabText = stringResource(R.string.my_parking),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun BookingButton() {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_calendar_filled),
        tabText = stringResource(R.string.my_bookings),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun UserNameEmailSection() {
    Text(
        text = "Suhana Suhana",
        fontFamily = fontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        modifier = Modifier.padding(top = 30.dp)
    )

    Text(
        text = "Suhanasuhana1234@gmail.com",
        fontFamily = fontFamily,
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        color = Grey600,
        modifier = Modifier.padding(
            top = 3.dp,
            bottom = 23.dp
        )
    )
}


@Preview
@Composable
fun PreviewAccountScreen() {
    AccountScreen(navHostController = rememberNavController())
}
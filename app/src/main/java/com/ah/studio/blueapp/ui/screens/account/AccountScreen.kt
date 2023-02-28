package com.ah.studio.blueapp.ui.screens.account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.SessionManager
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.screens.account.domain.dto.userDetails.UserDetailsResponse
import com.ah.studio.blueapp.ui.theme.Grey600
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.fontFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    onEditProfileClick: () -> Unit,
    onContactUsClick: () -> Unit,
    onTermConditionClick: () -> Unit,
    onRefundPolicyClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    viewModel: AccountViewModel = getKoin().get()
) {
    val sessionManager = SessionManager(LocalContext.current)
    var isLoading by remember {
        mutableStateOf(true)
    }
    var userDetails: UserDetailsResponse? by remember {
        mutableStateOf(null)
    }
    var showDialog by remember { mutableStateOf(false) }
    var isCircularProgressLoading by remember {
        mutableStateOf(false)
    }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getUserDetails()
            viewModel.userDetails.collectLatest { response ->
                userDetails = response
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon =null,
                navigationIconContentDescription = "",
                text = stringResource(R.string.account),
                actionIcons = {},
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
    ) {
        Box {
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
                if (userDetails != null) {
                    isLoading = false
                    UserNameEmailSection(userDetails!!)
                    EditProfileButton {
                        onEditProfileClick()
                    }
                    TermsAndConditionButton {
                        onTermConditionClick()
                    }
                    ContactUsButton {
                        onContactUsClick()
                    }
                    RefundPolicyButton {
                        onRefundPolicyClick()
                    }
                    PushNotificationButton()
                    ChangePasswordButton {
                        onChangePasswordClick()
                    }
                    LogoutButton {
                        showDialog = true
                    }
                }
            }
            if (isLoading) {
                CircularProgressBar()
            }
            if (isCircularProgressLoading) {
                CircularProgressBar()
            }
            if (showDialog) {
                ConfirmationDialog(
                    showDialog = showDialog,
                    onConfirm = {
                        showDialog = false
                        isCircularProgressLoading = true
                        CoroutineScope(Dispatchers.Main).launch {
                            viewModel.getLogoutResponse()
                            viewModel.logoutResponse.collectLatest { response ->
                                if (response != null) {
                                    if (response.status == 200) {
                                        isCircularProgressLoading = false
                                        sessionManager.saveToken("")
                                        onLogoutClick()
                                    }
                                }
                            }
                        }
                    },
                    onCancel = {
                        showDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun LogoutButton(
    onLogoutClick: () -> Unit
) {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_logout_filled),
        tabText = stringResource(R.string.logout),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier
            .padding(bottom = 12.dp)
            .clickable { onLogoutClick() }
    )
}

@Composable
fun ChangePasswordButton(
    onChangePasswordClick: () -> Unit
) {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_lock),
        tabText = stringResource(R.string.change_password),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier
            .padding(bottom = 12.dp)
            .clickable { onChangePasswordClick() }
    )
}

@Composable
fun EditProfileButton(
    onEditProfileClick: () -> Unit
) {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_profile_filled),
        tabText = stringResource(R.string.edit_profile),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier
            .padding(bottom = 12.dp)
            .clickable { onEditProfileClick() }
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
fun RefundPolicyButton(
    onRefundPolicyClick: () -> Unit
) {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_refund_document),
        tabText = stringResource(R.string.refund_policy),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier
            .padding(bottom = 12.dp)
            .clickable { onRefundPolicyClick() }
    )
}

@Composable
fun ContactUsButton(
    onContactUsClick: () -> Unit
) {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_contact_us),
        tabText = stringResource(R.string.contact_us),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier
            .padding(bottom = 12.dp)
            .clickable { onContactUsClick() }
    )
}

@Composable
fun TermsAndConditionButton(onTermConditionClick: () -> Unit) {
    TabButton(
        tabIcon = painterResource(id = R.drawable.ic_document_filled),
        tabText = stringResource(R.string.terms_and_condition),
        trailingIcon = painterResource(id = R.drawable.ic_arrow_right),
        modifier = Modifier
            .padding(bottom = 12.dp)
            .clickable { onTermConditionClick() }
    )
}

@Composable
fun UserNameEmailSection(userDetails: UserDetailsResponse) {
    Text(
        text = "${userDetails.data.first_name} ${userDetails.data.last_name}",
        fontFamily = fontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        modifier = Modifier.padding(top = 30.dp)
    )

    Text(
        text = userDetails.data.email,
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
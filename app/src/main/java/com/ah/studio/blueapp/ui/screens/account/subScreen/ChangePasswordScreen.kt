package com.ah.studio.blueapp.ui.screens.account.subScreen


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.Button
import com.ah.studio.blueapp.ui.component.CircularProgressBar
import com.ah.studio.blueapp.ui.component.CustomTextField
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.account.AccountViewModel
import com.ah.studio.blueapp.ui.screens.account.domain.dto.changePassword.ChangePasswordBody
import com.ah.studio.blueapp.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(
    viewModel: AccountViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(false)
    }
    var currentPassword by remember {
        mutableStateOf("")
    }
    var newPassword by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var snackbar by remember { mutableStateOf("") }
    var showSnackBar by remember { mutableStateOf(false) }
    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                if (showSnackBar) {
                    Snackbar(
                        actionColor = SeaBlue400,
                        contentColor = Color.Black,
                        snackbarData = data,
                        containerColor = Color.White,
                        shape = RoundedCornerShape(50.dp)
                    )
                }
            }
        },
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
        Box {
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
                    textInput = currentPassword,
                    value = { current ->
                        currentPassword = current
                    }
                )

                CustomTextField(
                    label = stringResource(id = R.string.new_password),
                    placeholder = stringResource(id = R.string.new_password),
                    textInput = newPassword,
                    value = { new ->
                        newPassword = new
                    },
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                )
                CustomTextField(
                    label = stringResource(id = R.string.confirm_password),
                    placeholder = stringResource(id = R.string.confirm_password),
                    textInput = confirmPassword,
                    value = { confirm ->
                        confirmPassword = confirm
                    },
                    imeAction = ImeAction.Done,
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
                ) {
                    CoroutineScope(Dispatchers.Main).launch {
                        isLoading = true
                        if (newPassword != confirmPassword) {
                            snackbar = "Password didn't matched!!"
                            showSnackBar = true
                            isLoading = false
                        } else if (newPassword.length < 6) {
                            snackbar = "The new password must be at least 6 characters.!!"
                            showSnackBar = true
                            isLoading = false
                        } else {
                            viewModel.getChangePasswordResponse(
                                ChangePasswordBody(
                                    confirm_password = confirmPassword,
                                    new_password = newPassword,
                                    old_password = currentPassword
                                )
                            )
                            viewModel.changePasswordResponse.collectLatest { response ->
                                if (response != null) {
                                    if (response.success) {
                                        snackbar = "Password Changed Successfully!!"
                                        currentPassword = ""
                                        newPassword = ""
                                        confirmPassword = ""
                                        showSnackBar = true
                                        isLoading = false
                                    } else {
                                        snackbar = response.message.toString()
                                        showSnackBar = true
                                        isLoading = false
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (isLoading) {
                CircularProgressBar()
            }
        }
    }
    if (showSnackBar) {
        SideEffect {
            scope.launch {
                snackbarHostState.showSnackbar(
                    snackbar, duration = SnackbarDuration.Short
                )
                showSnackBar = false
            }
        }
    }
}

@Preview
@Composable
fun PreviewChangePasswordScreen() {
    ChangePasswordScreen()
}
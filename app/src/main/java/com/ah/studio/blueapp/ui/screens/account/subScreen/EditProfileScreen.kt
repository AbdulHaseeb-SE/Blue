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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.Button
import com.ah.studio.blueapp.ui.component.CircularProgressBar
import com.ah.studio.blueapp.ui.component.CustomTextField
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.account.AccountViewModel
import com.ah.studio.blueapp.ui.screens.account.domain.dto.updateUser.UserProfileDetails
import com.ah.studio.blueapp.ui.screens.account.domain.dto.userDetails.UserDetails
import com.ah.studio.blueapp.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel: AccountViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(true)
    }

    var userDetails: UserDetails? by remember {
        mutableStateOf(null)
    }

    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var phoneNumber by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var civilId by remember {
        mutableStateOf("")
    }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getUserDetails()
            viewModel.userDetails.collectLatest { response ->
                if (response != null) {
                    userDetails = response.data
                }
            }
        }
        isLoading = false
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
                text = stringResource(id = R.string.edit_profile),
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
                if (userDetails != null) {
                    CustomTextField(
                        label = stringResource(id = R.string.first_name),
                        placeholder = userDetails!!.first_name,
                        value = {
                            firstName = it
                        }
                    )

                    CustomTextField(
                        label = stringResource(id = R.string.last_name),
                        placeholder = userDetails!!.last_name,
                        value = {
                            lastName = it
                        },
                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)
                    )

                    CustomTextField(
                        label = stringResource(id = R.string.phone_number),
                        placeholder = userDetails!!.phone_no,
                        value = {
                            phoneNumber = it
                        },
                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)
                    )

                    CustomTextField(
                        label = stringResource(id = R.string.email),
                        placeholder = userDetails!!.email,
                        value = {
                            email = it
                        },
                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)
                    )


                    CustomTextField(
                        label = stringResource(id = R.string.civil_id),
                        placeholder = userDetails!!.civil_id,
                        keyboardType = KeyboardType.Password,
                        modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
                        imeAction = ImeAction.Done,
                        value = {
                            civilId = it
                        }
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
                            .padding(horizontal = 46.dp, vertical = 61.dp)
                    ) {
                        CoroutineScope(Dispatchers.Main).launch {
                            isLoading = true
                            viewModel.getUpdateUserDetailsResponse(
                                UserProfileDetails(
                                    civil_id = civilId.ifEmpty { userDetails!!.civil_id },
                                    email = email.ifEmpty { userDetails!!.email },
                                    first_name = firstName.ifEmpty { userDetails!!.first_name },
                                    last_name = lastName.ifEmpty { userDetails!!.last_name },
                                    phone_no = phoneNumber.ifEmpty { userDetails!!.phone_no }
                                )
                            )
                            viewModel.updateUserDetailsResponse.collectLatest { response ->
                                if (response != null) {
                                    if (response.success) {
                                        isLoading = false
                                        snackbar = response.message
                                        showSnackBar = true
                                    } else {
                                        isLoading = false
                                        snackbar = response.message
                                        showSnackBar = true
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

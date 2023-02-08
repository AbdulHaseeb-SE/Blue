package com.ah.studio.blueapp.ui.screens.authentication

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.SessionManager
import com.ah.studio.blueapp.ui.component.Button
import com.ah.studio.blueapp.ui.component.CustomTextField
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.register.User
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.ApiConstants.FCM_TOKEN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@Composable
fun SignUpScreen(
    onRegisterClick: () -> Unit,
    onSignInClick: () -> Unit,
    viewModel: AuthenticationViewModel = getKoin().get()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    var snackbar by remember { mutableStateOf("") }
    var showSnackBar by remember { mutableStateOf(false) }
    val sessionManager = SessionManager(LocalContext.current)
    var token by remember {
        mutableStateOf("")
    }

    sessionManager.getToken()
        ?.let { it1 ->
            token = it1
        } ?: FCM_TOKEN


    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    actionColor = Green,
                    contentColor = Black,
                    snackbarData = data,
                    backgroundColor = White,
                    shape = RoundedCornerShape(50.dp),
                    elevation = 2.dp
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(it)
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_top_gradient),
                contentDescription = "",
                modifier = Modifier.padding(start = PaddingTripleLarge)
            )

            RegistrationForm(
                onSignInClick = { onSignInClick() },
                onRegisterClick = { firstName, lastName, phoneNumber, email, password, civilId ->
                    isLoading = true
                    try {
                        CoroutineScope(Dispatchers.Main).launch {
                            viewModel.registerUserResponse(
                                User(
                                    civil_id = civilId,
                                    email = email,
                                    first_name = firstName,
                                    last_name = lastName,
                                    password = password,
                                    phone_no = phoneNumber,
                                    fcm_token = token
                                )
                            )
                            viewModel.registerResponse.collectLatest { response ->
                                if (response != null) {
                                    when {
                                        response.isSuccessful -> {
                                            snackbar = "User registered successfully"
                                            onRegisterClick()
                                            showSnackBar = true
                                            isLoading = false
                                        }
                                        else -> {
                                            snackbar = response.message()
                                                .ifEmpty { "The email has already registered" }
                                            showSnackBar = true
                                            isLoading = false
                                        }
                                    }
                                }
                            }
                        }
                    } catch (e: Exception) {
                        viewModel.handleError(e)
                        snackbar = "Error registering user, try again!"
                        showSnackBar = true
                        isLoading = false
                    }
                },
                passwordNotMatched = {
                    showSnackBar = true
                    snackbar = "Password didn't matched"
                },
                textFieldsEmpty = {
                    showSnackBar = true
                    snackbar = "Fill all the fields to get registered."
                }
            )
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(White.copy(alpha = 0.7f))
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.Center),
                    color = SeaBlue400,
                    strokeWidth = 4.dp
                )
            }
        }
    }
    if (showSnackBar) {
        LaunchedEffect(key1 = true) {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    snackbar, duration = SnackbarDuration.Short
                )
                showSnackBar = false
            }
        }
        showSnackBar = false
    }
}

@Composable
fun RegistrationForm(
    onSignInClick: () -> Unit,
    onRegisterClick: (firstName: String, lastName: String, phoneNumber: String, email: String, password: String, civilId: String) -> Unit,
    passwordNotMatched: () -> Unit,
    textFieldsEmpty: () -> Unit,
    modifier: Modifier = Modifier
) {
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
    var password by remember {
        mutableStateOf("")
    }
    var civilId by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    var isPasswordMatched by remember {
        mutableStateOf(false)
    }

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
            fontFamily = fontFamily,
            modifier = modifier.padding(vertical = 58.dp)
        )
        CustomTextField(
            label = stringResource(R.string.first_name),
            placeholder = stringResource(R.string.first_name)
        ) {
            firstName = it
        }
        CustomTextField(
            label = stringResource(R.string.last_name),
            placeholder = stringResource(R.string.last_name)
        ) {
            lastName = it
        }
        CustomTextField(
            label = stringResource(R.string.phone_number),
            placeholder = stringResource(R.string.plus965),
            keyboardType = KeyboardType.Phone
        ) {
            phoneNumber = it
        }
        CustomTextField(
            label = stringResource(R.string.email),
            placeholder = stringResource(R.string.email),
            keyboardType = KeyboardType.Email
        ) {
            email = it
        }
        CustomTextField(
            label = stringResource(R.string.password),
            placeholder = stringResource(R.string.password),
            keyboardType = KeyboardType.Password
        ) {
            password = it
        }
        CustomTextField(
            label = stringResource(R.string.confirm_password),
            placeholder = stringResource(R.string.confirm_password),
            keyboardType = KeyboardType.Password
        ) {
            confirmPassword = it
            isPasswordMatched = it == password
        }
        CustomTextField(
            label = stringResource(R.string.civil_id),
            placeholder = stringResource(R.string.civil_id),
            imeAction = ImeAction.Done
        ) {
            civilId = it
        }

        Button(
            width = 280.dp,
            height = 50.dp,
            text = stringResource(id = R.string.register),
            backgroundColor = SeaBlue400,
            shape = Shapes.medium,
            modifier = modifier.padding(top = PaddingDouble)
        ) {
            if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || password.isEmpty() || civilId.isEmpty() || confirmPassword.isEmpty()) {
                textFieldsEmpty()
            } else {
                if (isPasswordMatched) {
                    onRegisterClick(firstName, lastName, phoneNumber, email, password, civilId)
                } else {
                    passwordNotMatched()
                }
            }

        }
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
                color = Black,
                fontFamily = fontFamily
            )
            Text(
                text = stringResource(R.string.signIn),
                fontSize = 16.sp,
                color = Black,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                modifier = modifier.clickable {
                    onSignInClick()
                }
            )
        }
    }
}
package com.ah.studio.blueapp.ui.screens.authentication

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.SessionManager
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.screens.authentication.domain.dto.login.LoginCredentials
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.ApiConstants.FCM_TOKEN
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignInScreen(
    onLoginClick: () -> Unit,
    onForgotClick: () -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: AuthenticationViewModel = getKoin().get()
) {
    val activity = (LocalContext.current as? Activity)
    BackHandler {
        activity?.finish()
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var snackbar by remember { mutableStateOf("") }
    var showSnackBar by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
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
                    actionColor = Color.Green,
                    contentColor = Color.Black,
                    snackbarData = data,
                    backgroundColor = Color.White,
                    shape = RoundedCornerShape(50.dp),
                    elevation = 2.dp
                )
            }
        },
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
            Sign_In(
                onLoginClick = { email, password ->
                    isLoading = true
                    try {
                        CoroutineScope(Dispatchers.Main).launch {
                            val response = viewModel.loginUserResponse(
                                LoginCredentials(
                                    email = email,
                                    password = password,
                                    fcm_token = if (token == "") FCM_TOKEN else token
                                )
                            )
                            response.collectLatest { _response ->
                                if (_response != null) {
                                    when (_response) {
                                        "true" -> {
                                            showSnackBar = true
                                            snackbar = "Logged In Successfully!"
                                            onLoginClick()
                                            isLoading = false
                                        }
                                        "false" -> {
                                            snackbar = "The entered Email or Password is Incorrect!"
                                            showSnackBar = true
                                            isLoading = false
                                        }
                                        else -> {
                                            snackbar = _response
                                                .ifEmpty { "An Error Occurred, try again later!" }
                                            showSnackBar = true
                                            isLoading = false
                                        }
                                    }
                                }
                            }
                        }
                    } catch (e: Exception) {
                        viewModel.handleError(e)
                        snackbar = "Error Logging user, try again!"
                        showSnackBar = true
                        isLoading = false
                    }
                },
                onForgotClick,
                onSignUpClick,
                textFieldsEmpty = {
                    snackbar = "Email or Password is Empty!"
                    showSnackBar = true
                }
            )
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.7f))
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
fun Sign_In(
    onLoginClick: (email: String, password: String) -> Unit,
    onForgotClick: () -> Unit,
    onSignUpClick: () -> Unit,
    textFieldsEmpty: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
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
            ) {
                email = it
            }

            PasswordTextField(
                label = stringResource(id = R.string.password),
                placeholder = stringResource(id = R.string.password),
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ) {
                password = it
            }

            Text(
                text = stringResource(R.string.forgot_password),
                fontSize = 16.sp,
                textAlign = TextAlign.End,
                color = Color.Black,
                fontFamily = fontFamily,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 2.dp)
                    .clickable {
                        onForgotClick()
                    }
            )

            Button(
                width = 285.dp,
                height = 50.dp,
                text = stringResource(id = R.string.login),
                backgroundColor = SeaBlue400,
                shape = Shapes.medium,
                modifier = modifier.padding(top = PaddingLarge)
            ) {
                if (email.isEmpty() || password.isEmpty()) {
                    textFieldsEmpty()
                } else {
                    onLoginClick(
                        email,
                        password
                    )
                }
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

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 32.dp,
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
                        onSignUpClick()
                    }
                )
            }
        }
    }
}

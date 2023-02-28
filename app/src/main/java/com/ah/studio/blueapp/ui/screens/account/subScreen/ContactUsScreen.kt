package com.ah.studio.blueapp.ui.screens.account.subScreen


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.screens.account.AccountViewModel
import com.ah.studio.blueapp.ui.screens.account.domain.dto.contactUs.ContactUsBody
import com.ah.studio.blueapp.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactUsScreen(
    onBackButtonClick: () -> Unit,
    viewModel: AccountViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(false)
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
                text = stringResource(id = R.string.contact_us),
                actionIcons = {},
                onNavigationIconClick = {
                    onBackButtonClick()
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .animateContentSize(),
        containerColor = Color.White,
    ) { paddingValues ->

        var yourReviews by remember {
            mutableStateOf("")
        }
        var name by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }

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
                    label = stringResource(id = R.string.name),
                    placeholder = stringResource(id = R.string.name),
                    value = {
                        name = it
                    }
                )

                CustomTextField(
                    label = stringResource(id = R.string.email),
                    placeholder = stringResource(id = R.string.email),
                    value = {
                        email = it
                    },
                    modifier = Modifier.padding(top = 17.dp, bottom = 17.dp)
                )

                BlueRoundedCornerShape(
                    containerColor = Color.White,
                    borderColor = OxfordBlue900Percent37,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 57.dp)
                        .wrapContentHeight()
                        .animateContentSize()
                ) {
                    TextField(
                        value = yourReviews,
                        onValueChange = { newText ->
                            yourReviews = newText
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.your_reviews),
                                fontSize = 16.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Normal
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            cursorColor = Color.Black,
                            placeholderColor = Color.Black,
                            textColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = fontFamily
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Default
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp)
                    )
                }

                Button(
                    width = 0.dp,
                    height = 50.dp,
                    text = stringResource(id = R.string.submit),
                    backgroundColor = SeaBlue400,
                    shape = Shapes.medium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 46.dp)
                ) {
                    CoroutineScope(Dispatchers.IO).launch {
                        isLoading = true
                        if (name == "" || email == "" || yourReviews == "") {
                            snackbar = "Kindly Fill all the fields!!!"
                            showSnackBar = true
                            isLoading = false
                        } else {
                            viewModel.getContactUsResponse(
                                ContactUsBody(
                                    name = name,
                                    email = email,
                                    answer = yourReviews
                                )
                            )
                            viewModel.contactUsResponse.collectLatest { response ->
                                if (response != null) {
                                    if (response.status == 200) {
                                        snackbar = "Your Reviews Sent Successfully!"
                                        showSnackBar = true
                                        isLoading = false
                                    } else {
                                        snackbar = response.message
                                        showSnackBar = true
                                        isLoading = false
                                    }
                                }
                            }
                        }
                    }
                }

                Text(
                    text = stringResource(R.string.follow_us),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    modifier = Modifier.padding(
                        top = 52.dp,
                        bottom = 12.dp
                    )
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_instagram),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(49.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_twitter),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(49.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(49.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_snapchat),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(49.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_whatsapp),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(49.dp)
                    )
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

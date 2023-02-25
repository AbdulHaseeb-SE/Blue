package com.ah.studio.blueapp.ui.screens.home.subScreens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.ah.studio.blueapp.ui.screens.home.HomeViewModel
import com.ah.studio.blueapp.ui.screens.home.domain.dto.booking.Product
import com.ah.studio.blueapp.ui.screens.home.domain.dto.cart.CartItems
import com.ah.studio.blueapp.ui.screens.home.domain.dto.delete.DeleteCartResponse
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.BookingDetailsManager
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onProceedToCheckoutClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    viewModel: HomeViewModel = getKoin().get()
) {
    val bookingDetailsManager = BookingDetailsManager(LocalContext.current)
    var isLoading by remember { mutableStateOf(true) }
    var cartList: List<CartItems>? by remember { mutableStateOf(null) }
    var responseStatus by remember { mutableStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var snackbar by remember { mutableStateOf("") }
    var showSnackBar by remember { mutableStateOf(false) }
    val productList: MutableList<Product>? by remember { mutableStateOf(mutableListOf()) }


    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading = true
            viewModel.getCartListResponse()
            viewModel.cartListResponse.collectLatest { response ->
                if (response != null) {
                    responseStatus = response.status
                    cartList = response.data
                    isLoading = false
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                if (showSnackBar) {
                    androidx.compose.material3.Snackbar(
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
                text = stringResource(id = R.string.cart),
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
        var queryText by remember {
            mutableStateOf("")
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    start = 10.dp,
                    end = 10.dp
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (!cartList.isNullOrEmpty()) {
                isLoading = false
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    LazyColumn {
                        itemsIndexed(cartList!!) { index, item ->
                            CartItem(
                                itemName = item.name,
                                qty = item.qty,
                                itemImage = coilImageLoadingAsync(imageUrl = item.image),
                                price = item.total + " KWD",
                                timeRequired = "30 Mins",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp)
                            ) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    val response = onDeleteClick(id = item.id, viewModel)
                                    if (response.value?.status == 200) {
                                        // remove the deleted item from the cartList
                                        cartList =
                                            cartList?.toMutableList()?.also { it.removeAt(index) }
                                    } else {
                                        // show error snackbar
                                        snackbar =
                                            response.value?.message ?: "Deleting Item from cart..."
                                        showSnackBar = true
                                    }
                                }
                            }
                            Divider(
                                thickness = 1.dp,
                                color = Black19Percent,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(
                                        top = 10.dp,
                                        bottom = 23.dp
                                    )
                            )
                        }
                        item {
                            Text(
                                text = stringResource(R.string.queries),
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = fontFamily,
                                color = Color.Black,
                            )

                            BlueRoundedCornerShape(
                                containerColor = Color.White,
                                borderColor = OxfordBlue900Percent37,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp)
                                    .wrapContentHeight()
                                    .animateContentSize()
                            ) {
                                TextField(
                                    value = queryText,
                                    onValueChange = { newText ->
                                        queryText = newText
                                    },
                                    placeholder = {
                                        Text(
                                            text = stringResource(R.string.option_to_write),
                                            fontSize = 15.sp,
                                            fontFamily = fontFamily
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
                                        fontSize = 15.sp,
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
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.empty_cart),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fontFamily,
                        color = Color.Black,
                    )
                }
            }
            Button(
                width = 0.dp,
                height = 50.dp,
                text = stringResource(id = R.string.proceed_to_checkout),
                backgroundColor = SeaBlue400,
                shape = Shapes.medium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 46.dp,
                        end = 46.dp,
                        bottom = PaddingDouble,
                        top = 46.dp
                    )
            ) {
                if (!cartList.isNullOrEmpty()) {
                    cartList!!.forEach { cartItem ->
                        productList?.plusAssign(
                            Product(
                                grand_total = cartItem.total,
                                product_id = cartItem.product_id.toString(),
                                qty = cartItem.qty.toString(),
                                type = cartItem.type
                            )
                        )
                    }

                    productList?.let {
                        bookingDetailsManager.saveProductList(
                            "product",
                            it
                        )
                    }

                    onProceedToCheckoutClick()

                } else {
                    onProceedToCheckoutClick()
                }
            }
            if (isLoading) {
                CircularProgressBar()
            }
        }
    }
    if (showSnackBar) {
        LaunchedEffect(key1 = true) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    snackbar, duration = SnackbarDuration.Short
                )
                showSnackBar = false
            }
        }
    }
}

fun onDeleteClick(id: Int, viewModel: HomeViewModel): MutableState<DeleteCartResponse?> {
    val responseStatus: MutableState<DeleteCartResponse?> = mutableStateOf(null)
    CoroutineScope(Dispatchers.IO).launch {
        viewModel.getDeleteCartResponse(id)
        viewModel.deleteCartItemResponse.collectLatest { response ->
            if (response != null) {
                responseStatus.value = response
            }
            return@collectLatest
        }
    }
    return responseStatus
}

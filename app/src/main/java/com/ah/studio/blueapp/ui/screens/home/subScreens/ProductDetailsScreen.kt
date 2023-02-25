package com.ah.studio.blueapp.ui.screens.home.subScreens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.*
import com.ah.studio.blueapp.ui.screens.home.HomeViewModel
import com.ah.studio.blueapp.ui.screens.home.domain.dto.cart.CreateCartBody
import com.ah.studio.blueapp.ui.screens.home.domain.dto.product.ProductDetails
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDetailsScreen(
    productId: String?,
    onCartButtonClick: () -> Unit,
    onViewCartClick: () -> Unit,
    viewModel: HomeViewModel = getKoin().get()
) {
    var isLoading by remember { mutableStateOf(true) }
    var responseStatus by remember { mutableStateOf(0) }
    var cartResponseStatus by remember { mutableStateOf(0) }
    var productDetails: ProductDetails? by remember { mutableStateOf(null) }
    var quantity by remember { mutableStateOf(1) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var snackbar by remember { mutableStateOf("") }
    var showSnackBar by remember { mutableStateOf(false) }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            productId?.toInt()?.let {
                viewModel.getProductDetailsResponse(productId = it)
            }
            viewModel.productDetailsResponse.collectLatest { response ->
                if (response != null) {
                    responseStatus = response.status
                    productDetails = response.data
                    isLoading = false
                }
            }

        }
    }

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
                navigationIcon = null,
                navigationIconContentDescription = "",
                text = "",
                actionIcons = {
                    BlueRoundedCornerShape(
                        modifier = Modifier
                            .width(48.dp)
                            .height(35.dp)
                            .padding(end = PaddingDouble)
                            .clickable {
                                onCartButtonClick()
                            },
                        containerColor = SeaBlue25Percent,
                        borderColor = Color.Transparent
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_cart),
                                contentDescription = stringResource(
                                    R.string.button_cart
                                ),
                                modifier = Modifier
                                    .size(24.dp),
                                colorFilter = ColorFilter.tint(Color.White)
                            )
                        }
                    }
                },
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                if (productDetails != null) {
                    isLoading = false
                    RoundedCornerImageView(
                        painter = coilImageLoadingAsync(imageUrl = productDetails!!.image),
                        shape = RoundedCornerShape(
                            topEnd = 0.dp,
                            topStart = 0.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        ),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(370.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 11.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = productDetails!!.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.fillMaxWidth()
                        )


                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .wrapContentHeight(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = productDetails!!.price,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = fontFamily,
                                color = OxfordBlue900,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .fillMaxWidth(0.6f)
                            )
                            AddSubtractItem { qty ->
                                quantity = qty
                            }
                        }

                        Text(
                            text = stringResource(R.string.description),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 17.dp)
                        )


                        Text(
                            text = productDetails!!.description,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Light,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        )

                        Button(
                            width = 0.dp,
                            height = 50.dp,
                            text = stringResource(id = R.string.add_to_cart),
                            backgroundColor = SeaBlue400,
                            shape = Shapes.medium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 46.dp,
                                    end = 46.dp,
                                    top = 46.dp,
                                    bottom = PaddingDouble
                                )
                        ) {
                            if (quantity != 0) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    viewModel.getCartCreateResponse(
                                        CreateCartBody(
                                            id = 0,
                                            product_id = productDetails!!.id,
                                            qty = quantity,
                                            total = productDetails!!.price.toDouble()
                                                .toInt() * quantity
                                        )
                                    )
                                    viewModel.createCartResponse.collectLatest { response ->
                                        Log.d("CheckCartResponse", response.toString())
                                        if (response != null) {
                                            cartResponseStatus = response.status
                                            if (responseStatus == 200) {
                                                snackbar = "Item added to cart successfully!!"
                                                showSnackBar = true
                                            }
                                        }
                                    }
                                }
                            } else {
                                snackbar = "Item Quantity must be greater than 0!!"
                                showSnackBar = true
                            }
                        }

                        Button(
                            width = 0.dp,
                            height = 50.dp,
                            text = stringResource(id = R.string.view_cart),
                            backgroundColor = SeaBlue400,
                            shape = Shapes.medium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 46.dp,
                                    end = 46.dp,
                                    bottom = 46.dp
                                )
                        ) {
                            onViewCartClick()
                        }
                    }
                } else {
                    if (responseStatus == 200) {
                        isLoading = false
                        Text(
                            text = "Details Not Found",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Light,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        )
                    }
                }
            }
            if (isLoading) CircularProgressBar()
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
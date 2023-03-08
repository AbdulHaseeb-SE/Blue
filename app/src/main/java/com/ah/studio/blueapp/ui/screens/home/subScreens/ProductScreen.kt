package com.ah.studio.blueapp.ui.screens.home.subScreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BlueRoundedCornerShape
import com.ah.studio.blueapp.ui.component.CircularProgressBar
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.home.HomeViewModel
import com.ah.studio.blueapp.ui.screens.home.domain.dto.product.ProductCategory
import com.ah.studio.blueapp.ui.screens.home.domain.dto.product.ProductSubCategory
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@Composable
fun ProductScreen(
    onSubCategoryClick: (subCategoryId: Int, subCategoryName: String) -> Unit,
    onSkipButtonClick: () -> Unit,
    onBackButtonClick: () -> Unit,
    viewModel: HomeViewModel = getKoin().get()
) {
    var type by remember { mutableStateOf("Restaurant") }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val snackbar by remember { mutableStateOf("") }
    var showSnackBar by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    var isSubCategoryLoading by remember { mutableStateOf(false) }
    var productCategoryResponse: List<ProductCategory>? by remember { mutableStateOf(null) }
    var productSubCategoryResponse: List<ProductSubCategory>? by remember { mutableStateOf(null) }
    var selectedProductCategoryId by remember { mutableStateOf(1) }
    var responseStatus by remember { mutableStateOf(0) }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewModel.getProductCategoryResponse(type = type)
                viewModel.productCategoryResponse.collectLatest { response ->
                    productCategoryResponse = response?.data
                    isLoading = false
                }
            } catch (e: Exception) {
                Log.e("Exception", "${e.message.toString()} ${e.cause} ${e.localizedMessage}")
            }
        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                if (showSnackBar) {
                    Snackbar(
                        actionColor = SeaBlue400,
                        contentColor = Color.Black,
                        snackbarData = data,
                        backgroundColor = Color.White,
                        shape = RoundedCornerShape(50.dp),
                        elevation = 2.dp
                    )
                }
            }
        },
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                navigationIconContentDescription = stringResource(id = R.string.back_button),
                text = type,
                actionIcons = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = stringResource(
                            R.string.button_notification
                        ),
                        modifier = Modifier
                            .padding(end = PaddingDouble)
                            .size(24.dp)
                    )
                },
                onNavigationIconClick = {
                    onBackButtonClick()
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = it.calculateTopPadding()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                val selectedItemColor by remember {
                    mutableStateOf(SeaBlue400)
                }
                val unSelectedItemColor by remember {
                    mutableStateOf(Grey500)
                }
                var selectedIndex by remember {
                    mutableStateOf(0)
                }
                var selectedSubCategory by remember {
                    mutableStateOf("Restaurants")
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(bottom = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    itemsIndexed(
                        listOf(
                            "Restaurant",
                            "Accessories"
                        )
                    ) { index, item ->
                        if (selectedIndex == 0) {
                            selectedProductCategoryId = 1
                        }
                        LaunchedEffect(key1 = true) {
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.getProductSubCategoryResponse(selectedProductCategoryId.toString())
                                viewModel.productSubCategoryResponse.collectLatest { response ->
                                    if (response != null) {
                                        responseStatus = response.status
                                        productSubCategoryResponse = response.data
                                        isSubCategoryLoading = true
                                        if (productSubCategoryResponse?.isNotEmpty() == true) {
                                            selectedSubCategory =
                                                productSubCategoryResponse!![0].name
                                        }
                                    }
                                }
                            }
                        }
                        TabSection(
                            tabTitle = item,
                            dividerColor = if (index == selectedIndex) selectedItemColor else unSelectedItemColor
                        ) { clickedTabTitle ->
                            selectedIndex = index
                            type = clickedTabTitle
                            productCategoryResponse = listOf()
                            selectedProductCategoryId =
                                if (clickedTabTitle == "Accessories") 5 else 1
                            CoroutineScope(Dispatchers.IO).launch {
                                viewModel.getProductSubCategoryResponse(selectedProductCategoryId.toString())
                                viewModel.productSubCategoryResponse.collectLatest { response ->
                                    if (response != null) {
                                        responseStatus = response.status
                                        productSubCategoryResponse = response.data
                                        isSubCategoryLoading = true
                                        if (productSubCategoryResponse?.isNotEmpty() == true) {
                                            selectedSubCategory =
                                                productSubCategoryResponse!![0].name
                                        }
                                    }
                                }
                            }

                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    viewModel.getProductCategoryResponse(clickedTabTitle)
                                    viewModel.productCategoryResponse.collectLatest { response ->
                                        if (response != null) {
                                            responseStatus = response.status
                                            productCategoryResponse = response.data
                                            isLoading = false
                                        }
                                    }
                                } catch (e: Exception) {
                                    Log.e(
                                        "Exception",
                                        "${e.message.toString()} ${e.cause} ${e.localizedMessage}"
                                    )
                                }
                            }
                        }
                    }
                }

                if (productCategoryResponse?.isNotEmpty() == true) {
                    SubCategorySection(
                        selectedIndex,
                        productCategoryResponse
                    ) { category, indexSelected ->
                        selectedSubCategory = category
                        selectedProductCategoryId = indexSelected
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.getProductSubCategoryResponse(
                                selectedProductCategoryId.toString()
                            )
                            viewModel.productSubCategoryResponse.collectLatest { response ->
                                productSubCategoryResponse = response?.data
                                isSubCategoryLoading = true
                            }
                        }
                    }
                }
                Box {
                    if (!productSubCategoryResponse.isNullOrEmpty()) {
                        isSubCategoryLoading = false
                        ProductSubCategorySection(productSubCategoryResponse!!) { subCategoryId, name ->
                            onSubCategoryClick(subCategoryId, name)
                        }
                    } else {
                        if (responseStatus == 200) {
                            isSubCategoryLoading = false
                            Text(
                                text = "No Item found !!",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 24.sp,
                                fontFamily = fontFamily,
                                color = OxfordBlue900,
                                modifier = Modifier.padding(top = PaddingDouble)
                            )
                        }
                    }
                    if (isSubCategoryLoading) {
                        CircularProgressBar()
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = PaddingHalf, end = PaddingDouble),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BlueRoundedCornerShape(
                    containerColor = Color.White,
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(50.dp)
                        .clickable { onSkipButtonClick() }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.skip),
                            fontSize = 17.sp,
                            color = OxfordBlue900,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = PaddingHalf)
                        )
                    }
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
                scaffoldState.snackbarHostState.showSnackbar(
                    snackbar, duration = SnackbarDuration.Short
                )
                showSnackBar = false
            }
        }
    }
}

@Composable
fun TabSection(
    tabTitle: String,
    dividerColor: Color,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .wrapContentHeight()
            .clickable {
                onClick(tabTitle)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = tabTitle,
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = fontFamily,
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        )
        Divider(
            thickness = 1.dp,
            color = dividerColor,
            modifier = Modifier
                .requiredWidthIn(min = 180.dp, max = 220.dp)
                .requiredWidth(width = 180.dp)
        )
    }
}

@Composable
fun CategoryCard(
    title: String,
    icon: Painter,
    textColor: Color,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    BlueRoundedCornerShape(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .padding(horizontal = 4.dp)
            .clickable { onClick() },
        containerColor = containerColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 9.dp
                ),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = icon,
                contentDescription = "",
                colorFilter = ColorFilter.tint(contentColor),
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                color = textColor,
                maxLines = 2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(51.dp)
                    .padding(bottom = 15.dp)
            )
        }
    }
}

@Composable
fun SubCategorySection(
    selectedIndex: Int,
    productCategoryResponse: List<ProductCategory>?,
    onClick: (category: String, selectedIndex: Int) -> Unit,
) {

    var selectedCategoryAccessoriesIndex by remember {
        mutableStateOf(0)
    }
    var selectedCategoryRestaurantIndex by remember {
        mutableStateOf(0)
    }
    val unSelectedContainerColor by remember {
        mutableStateOf(SeaBlue08Percent)
    }
    val selectedContainerColor by remember {
        mutableStateOf(SeaBlue400)
    }
    val selectedTextColor by remember {
        mutableStateOf(Color.White)
    }
    val unSelectedTextColor by remember {
        mutableStateOf(Color.Black)
    }
    val selectedContentColor by remember {
        mutableStateOf(Color.White)
    }
    val unSelectedContentColor by remember {
        mutableStateOf(Blue900)
    }

    when (selectedIndex) {
        0 -> {
            selectedCategoryAccessoriesIndex = 0
            LazyRow(
                modifier = Modifier
                    .padding(
                        top = 35.dp,
                        start = 12.dp,
                        end = 12.dp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (!productCategoryResponse.isNullOrEmpty()) {
                    itemsIndexed(
                        productCategoryResponse
                    ) { index, item ->
                        CategoryCard(
                            title = item.name,
                            icon = coilImageLoadingAsync(imageUrl = item.image),
                            containerColor = if (index == selectedCategoryRestaurantIndex) selectedContainerColor else unSelectedContainerColor,
                            contentColor = if (index == selectedCategoryRestaurantIndex) selectedContentColor else unSelectedContentColor,
                            textColor = if (index == selectedCategoryRestaurantIndex) selectedTextColor else unSelectedTextColor
                        ) {
                            selectedCategoryRestaurantIndex = index
                            onClick(
                                "Restaurants",
                                item.id
                            )
                        }
                    }
                }
            }
        }
        1 -> {
            selectedCategoryRestaurantIndex = 0
            LazyRow(
                modifier = Modifier
                    .padding(
                        top = 35.dp,
                        start = 12.dp,
                        end = 12.dp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (!productCategoryResponse.isNullOrEmpty()) {
                    itemsIndexed(
                        productCategoryResponse
                    ) { index, item ->
                        CategoryCard(
                            title = item.name,
                            icon = coilImageLoadingAsync(imageUrl = item.image),
                            containerColor = if (index == selectedCategoryAccessoriesIndex) selectedContainerColor else unSelectedContainerColor,
                            contentColor = if (index == selectedCategoryAccessoriesIndex) selectedContentColor else unSelectedContentColor,
                            textColor = if (index == selectedCategoryAccessoriesIndex) selectedTextColor else unSelectedTextColor
                        ) {
                            selectedCategoryAccessoriesIndex = index
                            onClick(
                                "Accessories",
                                item.id
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductSubCategorySection(
    productSubCategoryResponse: List<ProductSubCategory>,
    onSubCategoryClick: (subCategoryId: Int, name: String) -> Unit
) {
    LazyColumn {
        itemsIndexed(
            productSubCategoryResponse
        ) { _, item ->
            CategoryCard(
                name = item.name,
                description = "",
                image = coilImageLoadingAsync(imageUrl = item.image),
                modifier = Modifier.clickable {
                    onSubCategoryClick(item.id, item.name)
                }
            )
        }
    }
}

@Composable
fun CategoryCard(
    name: String,
    description: String,
    image: Painter,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 30.dp,
                start = PaddingDouble,
                end = PaddingDouble
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        BlueRoundedCornerShape(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            containerColor = Color.White,
            borderColor = SeaBlue400
        ) {
            Image(
                painter = image,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
            )
        }
        Text(
            text = name,
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = fontFamily,
            color = Color.Black,
            maxLines = 1,
            modifier = Modifier.padding(start = 2.dp)
        )
        Text(
            text = description,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = fontFamily,
            color = Grey600,
            maxLines = 1
        )
    }
}

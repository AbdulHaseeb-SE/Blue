package com.ah.studio.blueapp.ui.screens.home.subScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.CircularProgressBar
import com.ah.studio.blueapp.ui.component.ProductItem
import com.ah.studio.blueapp.ui.component.SearchBar
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.home.HomeViewModel
import com.ah.studio.blueapp.ui.screens.home.domain.dto.product.Product
import com.ah.studio.blueapp.ui.theme.Grey600
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.PaddingHalf
import com.ah.studio.blueapp.ui.theme.fontFamily
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    subCategoryId: String,
    subCategoryName: String,
    onProductClick: (productId: Int) -> Unit,
    viewModel: HomeViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    var productListResponse: List<Product>? by remember {
        mutableStateOf(null)
    }
    var responseStatus by remember {
        mutableStateOf(0)
    }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getProductListResponse(page = 1, subCategoryId = subCategoryId)
            viewModel.productListResponse.collectLatest { response ->
                if (response != null) {
                    responseStatus = response.status
                    productListResponse = response.data as MutableList<Product>
                    isLoading = false
                }
            }
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                navigationIconContentDescription = "",
                text = stringResource(id = R.string.restaurants),
                actionIcons = {

                    Image(
                        painter = painterResource(id = R.drawable.ic_cart),
                        contentDescription = stringResource(
                            R.string.button_notification
                        ),
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .size(24.dp),
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                },
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
    ) {
        var searchText by remember {
            mutableStateOf("")
        }
        var originalProductListResponse: List<Product>? by remember {
            mutableStateOf(null)
        }

        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = it.calculateTopPadding(),
                        start = PaddingDouble,
                        end = PaddingDouble
                    ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = subCategoryName,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    maxLines = 1,
                    modifier = Modifier.padding(top = PaddingHalf)
                )

                Text(
                    text = "",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = fontFamily,
                    color = Grey600,
                    maxLines = 1,
                    modifier = Modifier.padding(bottom = 19.dp)
                )

                SearchBar(
                    text = searchText,
                    onTextChange = { changedText ->
                        searchText = changedText
                        if (originalProductListResponse == null || originalProductListResponse!!.isEmpty()) {
                            originalProductListResponse = productListResponse?.toMutableList()
                        }
                        productListResponse = if (searchText.isEmpty()) {
                            originalProductListResponse?.toMutableList()
                        } else {
                            originalProductListResponse?.filter { product ->
                                product.name.contains(searchText, ignoreCase = true)
                            } as MutableList<Product>
                        }
                    },
                    onSearchClicked = {},
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                if (!productListResponse.isNullOrEmpty()) {
                    isLoading = false
                    ProductListSection(productListResponse!!) { productId ->
                        onProductClick(productId)
                    }
                } else {
                    if (responseStatus == 200) {
                        isLoading = false
                        Text(
                            text = "No Data Found !!",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontFamily,
                            color = Grey600,
                            maxLines = 1,
                            modifier = Modifier.padding(bottom = 19.dp)
                        )
                    }
                }
            }
            if (isLoading) {
                CircularProgressBar()
            }
        }
    }
}

@Composable
fun ProductListSection(
    productList: List<Product>,
    onItemClick: (productId: Int) -> Unit
) {
    LazyColumn {
        itemsIndexed(
            productList
        ) { _, item ->
            ProductItem(
                itemName = item.name,
                itemDescription = item.description,
                itemImage = coilImageLoadingAsync(imageUrl = item.image),
                price = item.price,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .height(110.dp)
                    .clickable {
                        onItemClick(item.id)
                    }

            )
        }
    }
}

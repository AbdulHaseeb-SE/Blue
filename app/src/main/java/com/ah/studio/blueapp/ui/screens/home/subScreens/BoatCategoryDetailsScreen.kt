package com.ah.studio.blueapp.ui.screens.home.subScreens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BoatListCard
import com.ah.studio.blueapp.ui.component.HorizontalTabs
import com.ah.studio.blueapp.ui.component.SearchBar
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.home.HomeViewModel
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.SubCategory
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boats.Boat
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boats.BoatBody
import com.ah.studio.blueapp.ui.theme.Grey600
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.PaddingLarge
import com.ah.studio.blueapp.ui.theme.SeaBlue400
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@Composable
fun BoatCategoryDetailsScreen(
    categoryName: String,
    categoryId: Int,
    viewModel: HomeViewModel = getKoin().get(),
    onBoatCardClick: (boatId: Int) -> Unit
) {
    var boatList: List<Boat>? by remember {
        mutableStateOf(listOf())
    }
    var isLoading by remember { mutableStateOf(true) }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var snackbar by remember { mutableStateOf("") }
    var showSnackBar by remember { mutableStateOf(false) }

    var subCategoryList: List<SubCategory>? by remember {
        mutableStateOf(listOf())
    }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewModel.getBoatCategoryResponse()
                viewModel.subCategoryList.collectLatest { list ->
                    if (!list.isNullOrEmpty()) {
                        subCategoryList = list
                    }
                }
            } catch (e: Exception) {
                Log.e("Exception", "${e.message.toString()} ${e.cause} ${e.localizedMessage}")
            }
        }
    }
    var tabSelected by remember { mutableStateOf(1) }
    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            isLoading = true
            try {
                viewModel.getBoatResponse(
                    BoatBody(
                        category = categoryId.toString(),
                        sub_category = tabSelected.toString(),
                        page = 1
                    )
                )
                viewModel.boatResponse.collectLatest { boatResponse ->
                    if (boatResponse != null) {
                        boatList = boatResponse.data
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
                backgroundColor = Color.White,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = categoryName,
                navigationIconContentDescription = stringResource(R.string.back_button),
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
                onNavigationIconClick = {})
        }
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        vertical = it.calculateTopPadding(),
                        horizontal = PaddingDouble
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                if (subCategoryList != null && subCategoryList!!.isNotEmpty()) {
                    isLoading = false
                    CategoryTabs(
                        categoryList = subCategoryList,
                        modifier = Modifier.fillMaxWidth()
                    ) { id ->
                        Log.d("checkTab", id)
                        tabSelected = id.toInt()
                        boatList = listOf()
                        isLoading = true
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                viewModel.getBoatResponse(
                                    BoatBody(
                                        category = categoryId.toString(),
                                        sub_category = id,
                                        page = 1
                                    )
                                )
                                isLoading = true
                                viewModel.boatResponse.collectLatest { boatResponse ->
                                    if (boatResponse != null && boatResponse.data.isNotEmpty()) {
                                        showSnackBar = false
                                        boatList = boatResponse.data
                                        isLoading = false
                                    } else if (boatResponse?.data?.size == 0) {
                                        if (boatList?.isNotEmpty() == true) {
                                            snackbar = "No Boat found in this category."
                                            showSnackBar = true
                                        }
                                    }
                                }
                            } catch (e: Exception) {
                                if (!e.message.isNullOrEmpty()) {
                                    snackbar = e.message!!
                                    showSnackBar = true
                                }
                            }
                        }
                    }
                    when (tabSelected) {
                        tabSelected -> {
                            if (boatList != null && boatList!!.isNotEmpty()) {
                                BoatListSection(
                                    boatList as MutableList<Boat>,
                                    onBoatCardClick = { boatId ->
                                        onBoatCardClick(boatId)
                                    }
                                )
                            }
                        }
                    }
                }
            }
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White.copy(alpha = 0f))
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
fun CategoryTabs(
    categoryList: List<SubCategory>?,
    modifier: Modifier = Modifier,
    onTabSelected: (String) -> Unit
) {
    HorizontalTabs(
        categoryList = categoryList,
        backgroundColor = Color.White,
        selectedContentColor = Color.Black,
        unselectedContentColor = Grey600,
        indicatorActiveColor = SeaBlue400,
        modifier = modifier
    ) { id ->
        onTabSelected(id)
    }
}

@Composable
fun BoatListSection(
    boatList: MutableList<Boat>,
    onBoatCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchText by remember { mutableStateOf("") }
    var boatLists = boatList
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = PaddingLarge),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            text = searchText,
            onTextChange = {
                searchText = it
            },
            onSearchClicked = {}
        )
        if (searchText.isNotEmpty()) {
            boatLists = boatList.filter { boat ->
                boat.name.contains(searchText, ignoreCase = true)
            } as MutableList<Boat>
        }
        boatLists.forEach { boat ->
            BoatListCard(
                boatImage = coilImageLoadingAsync(imageUrl = boat.featured_image),
                boatName = boat.name,
                boatLocation = boat.pickup_address,
                boatPrice = stringResource(R.string.starting_from) + " " + boat.starting_from + " " +
                        stringResource(R.string.kwd),
                locationFontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(135.dp)
                    .padding(top = 21.dp)
                    .clickable {
                        onBoatCardClick(boat.id)
                    }
            )
        }
    }
}

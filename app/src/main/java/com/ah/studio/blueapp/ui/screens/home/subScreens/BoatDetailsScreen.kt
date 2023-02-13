package com.ah.studio.blueapp.ui.screens.home.subScreens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatDetails.BoatDetails
import com.ah.studio.blueapp.ui.screens.home.domain.dto.gallery.Gallery
import com.ah.studio.blueapp.ui.theme.*
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoatDetailsScreen(
    boatId: Int,
    onViewAllClick: () -> Unit,
    onBookNowClick: () -> Unit,
    onReviewsClick: () -> Unit,
    viewModel: HomeViewModel = getKoin().get()
) {
    Log.d("CheckBoatId", boatId.toString())

    var galleryDetails: List<Gallery>? by remember {
        mutableStateOf(listOf())
    }
    var boatDetails: BoatDetails? by remember {
        mutableStateOf(null)
    }

    var isLoading by remember { mutableStateOf(true) }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewModel.getBoatDetailsResponse(boatId = boatId)
                viewModel.boatDetailsResponse.collectLatest { response ->
                    response?.data?.forEach {
                        boatDetails = it
                    }
                }
            } catch (e: Exception) {
                Log.e("Exception", "${e.message.toString()} ${e.cause} ${e.localizedMessage}")
            }
        }
    }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewModel.getGalleryImageResponse(boatId = boatId)
                viewModel.galleryImagesResponse.collectLatest { response ->
                    galleryDetails = response?.data
                }
            } catch (e: Exception) {
                Log.e("Exception", "${e.message.toString()} ${e.cause} ${e.localizedMessage}")
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = painterResource(id = R.drawable.ic_back),
                text = "",
                navigationIconContentDescription = stringResource(id = R.string.back_button),
                actionIcons = {},
                onNavigationIconClick = {})
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        var imageClickedIndex: Int? by remember {
            mutableStateOf(null)
        }

        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(Color.White)
            ) {
                isLoading = true
                boatDetails
                    ?.let { it1 -> coilImageLoadingAsync(imageUrl = it1.featured_image) }
                    ?.let { boatImage ->
                        isLoading = false
                        RoundedCornerImageView(
                            painter = boatImage,
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
                    }

                BoatNameDetailsSection(boatDetails)

                BoatGallerySection(
                    boatDetails,
                    galleryDetails,
                    onViewAllClick = { onViewAllClick() },
                    onImageClick = { index ->
                        imageClickedIndex = index
                    }
                )
                FacilitiesSection(boatDetails)

                AddressSection(boatDetails)

                BottomSection(
                    onBookNowClick = {
                        isLoading = true
                        onBookNowClick()
                    },
                    onReviewsClick = {
                        onReviewsClick()
                    }
                )
            }
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
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
            if (imageClickedIndex != null) {
                galleryDetails?.let { list ->
                    ImageViewer(
                        images = list,
                        clickedImageIndex = imageClickedIndex!!
                    ) {
                        imageClickedIndex = null
                    }
                }
            }
        }
    }
}

@Composable
fun BottomSection(
    onBookNowClick: () -> Unit,
    onReviewsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 40.dp, start = PaddingDouble, end = PaddingDouble)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            width = 283.dp,
            height = 50.dp,
            text = stringResource(R.string.book_now),
            backgroundColor = SeaBlue400,
            shape = Shapes.medium
        ) {
            onBookNowClick()
        }
        Button(
            width = 283.dp,
            height = 50.dp,
            text = stringResource(R.string.reviews),
            backgroundColor = SeaBlue400,
            shape = Shapes.medium,
            modifier = Modifier.padding(top = PaddingDouble)
        ) {
            onReviewsClick()
        }
    }
}

@Composable
fun AddressSection(boatDetails: BoatDetails?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 23.dp, horizontal = PaddingDouble)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_circled_location),
                contentDescription = stringResource(R.string.location_icon),
            )

            Column(
                modifier = Modifier.padding(horizontal = 9.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.pick_up_address),
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                boatDetails?.pickup_address?.let {
                    Text(
                        text = it,
                        fontSize = 14.sp,
                        fontFamily = fontFamily,
                        color = Color.Black,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(Color.White)
                .padding(top = 5.dp)
        ) {

        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_captian_cap),
                contentDescription = stringResource(R.string.seafarer),
            )
            Column(
                modifier = Modifier.padding(horizontal = 9.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.seafarer),
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    color = Color.Black,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (boatDetails?.seafarer_name.isNullOrEmpty()) {
                    Text(
                        text = stringResource(R.string.no_seafarer_hired_yet),
                        fontSize = 17.sp,
                        fontFamily = fontFamily,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                } else {

                }
            }
        }
    }
}

@Composable
fun FacilitiesSection(boatDetails: BoatDetails?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Facilities",
            fontSize = 17.sp,
            fontFamily = fontFamily,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingDouble)
        )
        if (boatDetails?.facilities?.isNotEmpty() == true) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 21.dp, start = PaddingHalf, end = PaddingDouble),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LazyRow(content = {
                    itemsIndexed(
                        boatDetails.facilities
                    ) { _, item ->
                        RoundedCornerImageView(
                            painter = coilImageLoadingAsync(imageUrl = item.image),
                            shape = Shapes.medium,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .width(80.dp)
                                .height(80.dp)
                        )
                    }
                })
            }
        } else {
            Text(
                text = "No Facilities are available yet!!",
                fontSize = 17.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, start = PaddingDouble, end = PaddingDouble)
            )
        }
    }
}

@Composable
fun BoatGallerySection(
    boatDetails: BoatDetails?, galleryDetails: List<Gallery>?,
    onViewAllClick: () -> Unit,
    onImageClick: (index: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp)
            .background(Color.White)
    ) {
        if (galleryDetails.isNullOrEmpty()) {
            Text(
                text = "No data found in gallery !!",
                fontSize = 14.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PaddingDouble)
            )
        } else {
            Text(
                text = "View all",
                fontSize = 14.sp,
                fontFamily = fontFamily,
                color = Grey500,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PaddingDouble)
                    .clickable { onViewAllClick() }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, end = PaddingDouble),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LazyRow(content = {
                    itemsIndexed(
                        galleryDetails
                    ) { index, item ->
                        RoundedCornerImageView(
                            painter = coilImageLoadingAsync(imageUrl = item.image),
                            shape = Shapes.medium,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(124.dp)
                                .height(112.dp)
                                .padding(start = 16.dp)
                                .clickable {
                                    onImageClick(index)
                                }
                        )
                    }
                })
            }
        }


        boatDetails?.description?.let {
            Text(
                text = "Description",
                fontSize = 17.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = PaddingDouble,
                        end = PaddingDouble,
                        top = 70.dp
                    )
            )
            Text(
                text = it,
                fontSize = 17.sp,
                fontFamily = fontFamily,
                color = Color.Black,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = PaddingDouble,
                        end = PaddingDouble
                    )
            )
        }
    }
}

@Composable
fun BoatNameDetailsSection(boatDetails: BoatDetails?) {
    Column(
        modifier = Modifier
            .padding(
                horizontal = PaddingDouble,
                vertical = PaddingHalf
            )
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .background(Color.White)
            ) {
                boatDetails?.name?.let {
                    Text(
                        text = it,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Color.Black
                    )
                }

                StarRatingBar(
                    rating = if (boatDetails?.boat_rating != null) boatDetails.boat_rating as Double else 0.0,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(top = 5.dp)
                        .height(20.dp)
                )

            }
            Column(
                modifier = Modifier.wrapContentWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Starting from\n ${boatDetails?.starting_from} KWD",
                    fontSize = 17.sp,
                    fontFamily = fontFamily,
                    color = OxfordBlue900
                )
            }
        }
    }
}


package com.ah.studio.blueapp.ui.screens.home.subScreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.ImageViewer
import com.ah.studio.blueapp.ui.component.RoundedCornerImageView
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.home.HomeViewModel
import com.ah.studio.blueapp.ui.screens.home.domain.dto.gallery.Gallery
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.SeaBlue400
import com.ah.studio.blueapp.ui.theme.Shapes
import com.ah.studio.blueapp.util.coilImageLoadingAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleyScreen(
    boatId: Int,
    onBackButtonClick: () -> Unit,
    viewModel: HomeViewModel = getKoin().get()
) {
    var galleryDetails: List<Gallery>? by remember {
        mutableStateOf(listOf())
    }

    var isLoading by remember { mutableStateOf(true) }

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
                text = stringResource(R.string.gallery),
                navigationIconContentDescription = "",
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
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {

        var imageClickedIndex: Int? by remember {
            mutableStateOf(null)
        }



        Box {
            if (!galleryDetails.isNullOrEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 124.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            end = PaddingDouble,
                            top = it.calculateTopPadding()
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalArrangement = Arrangement.Top,
                    content = {
                        itemsIndexed(
                            galleryDetails!!
                        ) { index, item ->
                            isLoading = false
                            RoundedCornerImageView(
                                painter = coilImageLoadingAsync(imageUrl = item.image),
                                shape = Shapes.medium,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(124.dp)
                                    .height(112.dp)
                                    .padding(
                                        top = PaddingDouble,
                                        start = PaddingDouble,
                                    )
                                    .clickable {
                                        imageClickedIndex = index
                                    }
                            )
                        }
                    })
            } else {
                isLoading = false
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
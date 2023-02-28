package com.ah.studio.blueapp.ui.screens.seafarer

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat.startActivity
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BlueRoundedCornerShape
import com.ah.studio.blueapp.ui.component.CircularProgressBar
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerCategory.SeafarerCategoryResponse
import com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerList.Seafarer
import com.ah.studio.blueapp.ui.screens.seafarer.domain.dto.seafarerList.SeafarerListResponse
import com.ah.studio.blueapp.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeafarerScreen(
    onPayToUnlockClick: (captainId: Int, category: String) -> Unit,
    viewModel: SeafarerViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    var seafarerCategory by remember {
        mutableStateOf("captains")
    }
    var seafarerCategoryListResponse: SeafarerCategoryResponse? by remember {
        mutableStateOf(null)
    }
    var seafarerListResponse: SeafarerListResponse? by remember {
        mutableStateOf(null)
    }
    val context = LocalContext.current

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getSeafarerCategoryResponse()
            viewModel.seafarerCategoryResponse.collectLatest { response ->
                seafarerCategoryListResponse = response
            }
        }
    }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getSeafarerListResponse(
                page = 1,
                category = seafarerCategory
            )
            viewModel.seafarerListResponse.collectLatest { response ->
                seafarerListResponse = response
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                contentColor = Color.Black,
                navigationIcon = null,
                text = stringResource(id = R.string.seafarer_text),
                navigationIconContentDescription = "",
                actionIcons = {},
                onNavigationIconClick = {}
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = PaddingDouble,
                        end = PaddingDouble,
                        top = it.calculateTopPadding()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                if (seafarerCategoryListResponse != null) {
                    TabBarSection(seafarerCategoryListResponse) { itemId ->
                        seafarerCategory = itemId
                        isLoading = true
                        CoroutineScope(Dispatchers.IO).launch {
                            viewModel.getSeafarerListResponse(
                                page = 1,
                                category = seafarerCategory
                            )
                            viewModel.seafarerListResponse.collectLatest { response ->
                                seafarerListResponse = response
                            }
                        }
                    }
                    if (seafarerListResponse != null) {
                        isLoading = false
                        if (seafarerListResponse!!.data.isNotEmpty()) {
                            LazyColumn {
                                if (seafarerListResponse != null) {
                                    itemsIndexed(
                                        seafarerListResponse!!.data
                                    ) { _, item ->
                                        isLoading = false
                                        CaptainDetailCard(
                                            item,
                                            onCallClick = {
                                                isLoading = true
                                                val callUri = Uri.parse("tel:${item.phone_no}")
                                                val intent = Intent(Intent.ACTION_DIAL, callUri)
                                                startActivity(context, intent, null)
                                                isLoading = false
                                            },
                                            onMessageClick = {
                                                isLoading = true
                                                val smsUri = Uri.parse("smsto:${item.phone_no}")
                                                val intent = Intent(Intent.ACTION_SENDTO, smsUri)
                                                startActivity(context, intent, null)
                                                isLoading = false
                                            },
                                            onPayToUnlockClick = {
                                                if (seafarerListResponse != null) {
                                                    onPayToUnlockClick(
                                                        item.id,
                                                        item.category
                                                    )
                                                }
                                            }
                                        )
                                    }
                                }
                            }
                        } else if (seafarerListResponse?.data?.isEmpty() == true) {
                            Text(
                                text = "No Seafarer Found!!",
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = fontFamily,
                                color = Grey700,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = PaddingLarge)
                            )
                        }
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
fun CaptainDetailCard(
    seafarer: Seafarer,
    onMessageClick: () -> Unit,
    onCallClick: () -> Unit,
    onPayToUnlockClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .wrapContentHeight()
    ) {

        BlueRoundedCornerShape(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 19.dp,
                        bottom = 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = seafarer.name,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth(0.55f)
                        .padding(start = PaddingHalf)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_contact_us),
                    contentDescription = "",
                    modifier = Modifier.clickable { onCallClick() }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_chat),
                    contentDescription = "",
                    modifier = Modifier.clickable { onMessageClick() }
                )
            }

            Divider(
                thickness = (0.5).dp,
                color = Black30Percent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = PaddingDouble),
                horizontalAlignment = Alignment.Start
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 5.dp,
                            bottom = 9.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Text(
                            text = stringResource(R.string.experience_text),
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Grey700,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = seafarer.experince,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Row(
                        modifier = Modifier.wrapContentWidth(),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Text(
                            text = stringResource(R.string.age_text),
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Grey700,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = seafarer.age.toString() + " Years",
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 9.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(R.string.nationality_text),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Grey700,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = seafarer.nationality,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Color.Black,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(R.string.boat_experience),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Grey700,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    seafarer.baot_experince.forEach { experience ->
                        Text(
                            text = experience + if (experience == seafarer.baot_experince.last()) "." else ", ",
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 26.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(R.string.spoken_languages_text),
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Grey700,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    seafarer.language.forEach { language ->
                        Text(
                            text = language + if (language == seafarer.language.last()) "." else ",",
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        if (!seafarer.unlock) {
            PayToUnlock(
                onPayToUnlockClick = {
                    onPayToUnlockClick()
                }
            )
        }
    }
}

@Composable
fun PayToUnlock(onPayToUnlockClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_blurred_background),
            contentDescription = "",
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 18.dp
                )
                .background(White2Percent),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_lock_outlined),
                contentDescription = "",
                modifier = Modifier.padding(end = 12.dp)
            )
            Text(
                text = stringResource(R.string.pay_to_unlock),
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
                color = Red700,
                fontSize = 17.sp,
                modifier = Modifier.clickable { onPayToUnlockClick() }
            )
        }
    }
}

@Composable
fun TabBarSection(
    seafarerCategoryListResponse: SeafarerCategoryResponse?,
    onClick: (String) -> Unit
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    if (seafarerCategoryListResponse != null) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 9.dp,
                    bottom = 29.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            itemsIndexed(
                seafarerCategoryListResponse.data
            ) { index, item ->
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = PaddingDouble)
                ) {
                    val (text, divider) = createRefs()

                    Text(
                        text = item.name,
                        fontFamily = fontFamily,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (selectedIndex == index) Color.Black else Black50Percent,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .constrainAs(text) {
                                start.linkTo(parent.start)
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                            }
                            .clickable {
                                selectedIndex = index
                                onClick(item.id)
                            }
                    )

                    Divider(
                        thickness = (1.5).dp,
                        color = if (selectedIndex == index) Color.Cyan else Color.Transparent,
                        modifier = Modifier
                            .padding(top = PaddingHalf)
                            .constrainAs(divider) {
                                start.linkTo(text.start)
                                end.linkTo(text.end)
                                top.linkTo(text.bottom)
                                width = Dimension.fillToConstraints
                            }
                    )
                }


                /* Column {
                        Text(
                            text = item.name,
                            fontFamily = fontFamily,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            color = if (selectedIndex == index) Color.Black else Black50Percent,
                            modifier = Modifier
                                .wrapContentWidth()
                                .padding(end = 19.dp)
                                .clickable {
                                    selectedIndex = index
                                    onClick(item.id)
                                }
                        )
                        Divider(
                            thickness = 1.dp,
                            color = if (selectedIndex == index) Color.Cyan else Color.Transparent,
                            modifier = Modifier
                                .width(80.dp)
                                .padding(
                                    top = PaddingHalf
                                )
                        )
                    }*/
            }
        }
    }

}

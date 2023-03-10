package com.ah.studio.blueapp.ui.screens.account.subScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.CircularProgressBar
import com.ah.studio.blueapp.ui.component.HtmlWebView
import com.ah.studio.blueapp.ui.component.TopAppBar
import com.ah.studio.blueapp.ui.screens.account.AccountViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermAndConditionScreen(
    onBackButtonClick: () -> Unit,
    viewModel: AccountViewModel = getKoin().get()
) {
    var isLoading by remember {
        mutableStateOf(true)
    }
    var termAndCondition by remember {
        mutableStateOf("")
    }

    SideEffect {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getTermsAndConditions()
            viewModel.termsAndCondition.collectLatest { response ->
                if (response != null) {
                    termAndCondition = response.data.description
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
                text = stringResource(id = R.string.terms_and_condition),
                actionIcons = {},
                onNavigationIconClick = {
                    onBackButtonClick()
                }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.White,
    ) { paddingValues ->
        Box {
            if(termAndCondition!=""){
                isLoading = false
                Column(
                    modifier = Modifier
                        .padding(
                            top = paddingValues.calculateTopPadding() + 24.dp,
                            start = 2.dp,
                            end = 2.dp
                        )
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    HtmlWebView(html = termAndCondition)
                }
            }

            if (isLoading) {
                CircularProgressBar()
            }
        }
    }
}

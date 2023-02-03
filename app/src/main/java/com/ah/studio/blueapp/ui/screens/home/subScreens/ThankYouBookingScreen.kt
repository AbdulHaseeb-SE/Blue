package com.ah.studio.blueapp.ui.screens.home.subScreens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.component.BlueRoundedCornerShape
import com.ah.studio.blueapp.ui.theme.PaddingDouble
import com.ah.studio.blueapp.ui.theme.SeaBlue400
import com.ah.studio.blueapp.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ThankYouBookingScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(SeaBlue400),
            contentAlignment = Alignment.BottomCenter
        ) {
            BlueRoundedCornerShape(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = PaddingDouble,
                        vertical = 146.dp
                    )
                    .wrapContentHeight(),
                containerColor = Color.Transparent,
                borderColor = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = PaddingDouble, horizontal = 11.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.thank_you_for_booking),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily,
                        color = Color.White
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 45.dp,
                                bottom = 12.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            text = stringResource(R.string.order_id),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontFamily,
                            color = Color.White
                        )
                        Text(
                            text = "2333",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontFamily,
                            color = Color.White
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 12.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            text = stringResource(R.string.transaction_type),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontFamily,
                            color = Color.White
                        )
                        Text(
                            text = "KNET",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontFamily,
                            color = Color.White
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 12.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            text = stringResource(R.string.transaction_id),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontFamily,
                            color = Color.White
                        )
                        Text(
                            text = "2003933882818",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontFamily,
                            color = Color.White
                        )
                    }



                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 12.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            text = stringResource(R.string.payment_status),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontFamily,
                            color = Color.White
                        )
                        Text(
                            text = "Successful",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontFamily,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewThankYouBookingScreen(){
    ThankYouBookingScreen()
}

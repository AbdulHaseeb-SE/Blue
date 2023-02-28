package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.ui.theme.OxfordBlue900
import com.ah.studio.blueapp.ui.theme.SeaBlue08Percent
import com.ah.studio.blueapp.ui.theme.SeaBlue400
import com.ah.studio.blueapp.ui.theme.fontFamily
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun TimePicker(
    currentTime: LocalTime = LocalTime.now(),
    selectedTime: (LocalTime) -> Unit
) {

    var pickedTime by remember {
        mutableStateOf(currentTime)
    }

    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("k:mm")
                .format(pickedTime)
        }
    }

    val timeDialogState = rememberMaterialDialogState()

    BlueRoundedCornerShape(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .clickable {
                timeDialogState.show()
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formattedTime,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontFamily = fontFamily,
                modifier = Modifier.padding(
                    vertical = 12.dp,
                    horizontal = 47.dp
                )
            )
        }

        MaterialDialog(
            dialogState = timeDialogState,
            buttons = {
                positiveButton(
                    text = "Ok",
                    textStyle = TextStyle(
                        color = OxfordBlue900,
                        fontFamily = fontFamily,
                        fontSize = 17.sp
                    )
                ) {
                    selectedTime(pickedTime)
                }
                negativeButton(
                    text = "Cancel",
                    textStyle = TextStyle(
                        color = OxfordBlue900,
                        fontFamily = fontFamily,
                        fontSize = 17.sp
                    )
                )
            },
        ) {
            timepicker(
                initialTime = LocalTime.NOON,
                title = "Pick a time",
                colors = TimePickerDefaults.colors(
                    activeBackgroundColor = SeaBlue400,
                    inactiveBackgroundColor = SeaBlue08Percent,
                    selectorColor = SeaBlue400
                ),
                is24HourClock = true
            ) {
                pickedTime = it
            }
        }
    }
}


@Composable
@Preview
fun PreviewTimePicker() {
    TimePicker() {}
}
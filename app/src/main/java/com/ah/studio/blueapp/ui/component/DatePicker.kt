package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.ui.theme.OxfordBlue900
import com.ah.studio.blueapp.ui.theme.SeaBlue400
import com.ah.studio.blueapp.ui.theme.fontFamily
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DatePicker(
    currentDate: LocalDate = LocalDate.now(),
    selectedDate: (LocalDate) -> Unit
) {

    var pickedDate by remember {
        mutableStateOf(currentDate)
    }

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd MMM yyyy")
                .format(pickedDate)
        }
    }

    val dateDialogState = rememberMaterialDialogState()

    BlueRoundedCornerShape(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .clickable {
                dateDialogState.show()
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formattedDate,
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = fontFamily,
                modifier = Modifier.padding(
                    vertical = 12.dp,
                    horizontal = 26.dp
                )
            )
        }

        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "Ok") {
                    selectedDate(pickedDate)
                }
                negativeButton(text = "Cancel")
            },
        ) {
            datepicker(
                initialDate = LocalDate.now(),
                title = "Pick a date",
                colors = DatePickerDefaults.colors(
                    headerBackgroundColor = SeaBlue400,
                    dateActiveBackgroundColor = OxfordBlue900,
                    dateActiveTextColor = Color.White,
                )
//                allowedDateValidator = {  }
            ) {
                pickedDate = it
            }
        }
    }
}


@Composable
@Preview
fun PreviewDatePicker() {
    DatePicker(){}
}
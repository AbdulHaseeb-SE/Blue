package com.ah.studio.blueapp.ui.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.ui.theme.*

@Composable
fun TimeSlotTable(
    startingSlot: String,
    endingSlot: String,
    modifier: Modifier = Modifier,
    startingTime: (String) -> Unit,
    endingTime: (String) -> Unit
) {
    var startingSelectedSlot by remember {
        mutableStateOf(startingSlot)
    }
    var endingSelectedSlot by remember {
        mutableStateOf(endingSlot)
    }
    var isFirstSelect by remember {
        mutableStateOf(true)
    }
    var rangeStartIndex by remember {
        mutableStateOf(-1)
    }
    var rangeEndIndex by remember {
        mutableStateOf(-1)
    }

    val slots = listOf(
        listOf("00:00", "01:00", "02:00", "03:00", "04:00", "05:00"),
        listOf("06:00", "07:00", "08:00", "09:00", "10:00", "11:00"),
        listOf("12:00", "13:00", "14:00", "15:00", "16:00", "17:00"),
        listOf("18:00", "19:00", "20:00", "21:00", "22:00", "23:00")
    )

    if (rangeStartIndex == -1) {
        slots.forEachIndexed { rowIndex, row ->
            val index = row.indexOf(startingSlot)
            if (index != -1) {
                rangeStartIndex = rowIndex * 6 + index
                return@forEachIndexed
            }
        }
    }

    if (rangeEndIndex == -1) {
        slots.forEachIndexed { rowIndex, row ->
            val index = row.indexOf(endingSlot)
            if (index != -1) {
                rangeEndIndex = rowIndex * 6 + index
                return@forEachIndexed
            }
        }
    }

    LazyColumn(
        modifier = modifier.background(Color.White)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        itemsIndexed(
            slots
        ) { rowIndex, item ->
            val context = LocalContext.current

            BlueRoundedCornerShape(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(bottom = PaddingDouble),
                containerColor = Color.Transparent,
                borderColor = Color.Transparent,
                shape = RoundedCornerShape(50.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    item.forEachIndexed { index, text ->
                        val currentIndex = rowIndex * 6 + index
                        //this shape will be used to set the background color for items between startingSlot to endingSlot to SeaBlue50 else White.
                        BlueRoundedCornerShape(
                            modifier = Modifier.wrapContentSize(),
                            containerColor = if (currentIndex in rangeStartIndex..rangeEndIndex) SeaBlue50Percent else Color.White,
                            borderColor = Color.Transparent,
                            shape = when (text) {
                                startingSelectedSlot -> {
                                    if (text.indexOf(startingSelectedSlot) > text.indexOf(
                                            startingSlot
                                        ) &&
                                        index in 1..4
                                    ) {
                                        RoundedCornerShape(
                                            topStart = 0.dp,
                                            bottomStart = 0.dp,
                                        )
                                    } else if (index == 5) {
                                        RoundedCornerShape(
                                            topStart = 0.dp,
                                            bottomStart = 0.dp,
                                            topEnd = 50.dp,
                                            bottomEnd = 50.dp
                                        )
                                    } else {
                                        RoundedCornerShape(
                                            topStart = 50.dp,
                                            bottomStart = 50.dp
                                        )
                                    }
                                }
                                endingSelectedSlot -> {
                                    if (text.indexOf(endingSelectedSlot) > text.indexOf(
                                            endingSlot
                                        ) &&
                                        index in 1..4
                                    ) {
                                        RoundedCornerShape(
                                            topEnd = 0.dp,
                                            bottomEnd = 0.dp
                                        )
                                    } else if (index == 0) {
                                        RoundedCornerShape(
                                            topStart = 50.dp,
                                            bottomStart = 50.dp,
                                            topEnd = 0.dp,
                                            bottomEnd = 0.dp
                                        )
                                    } else {
                                        RoundedCornerShape(
                                            topEnd = 50.dp,
                                            bottomEnd = 50.dp
                                        )
                                    }
                                }
                                else -> {
                                    when (index) {
                                        0 -> RoundedCornerShape(
                                            topStart = 50.dp,
                                            bottomStart = 50.dp
                                        )
                                        5 -> RoundedCornerShape(
                                            topEnd = 50.dp,
                                            bottomEnd = 50.dp
                                        )
                                        else -> {
                                            RoundedCornerShape(0.dp)
                                        }
                                    }
                                }
                            }
                        ) {
                            BlueRoundedCornerShape(
                                modifier = Modifier.wrapContentSize(),
                                containerColor = if (text == startingSelectedSlot || text == endingSelectedSlot) OxfordBlue900 else Color.Transparent,
                                borderColor = if (text == startingSelectedSlot || text == endingSelectedSlot) SeaBlue400 else Color.Transparent,
                                shape = RoundedCornerShape(50.dp)
                            ) {
                                Text(
                                    text = text,
                                    fontFamily = fontFamily,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    maxLines = 1,
                                    color = if (text == startingSelectedSlot || text == endingSelectedSlot) Color.White else OxfordBlue900,
                                    modifier = Modifier
                                        .padding(
                                            horizontal = 10.dp,
                                            vertical = 8.dp
                                        )
                                        .clickable {

                                            if (isFirstSelect) {
                                                if (
                                                    currentIndex in rangeStartIndex..rangeEndIndex &&
                                                    currentIndex < rangeEndIndex
                                                ) {
                                                    if (
                                                        text < endingSelectedSlot
                                                    ) {
                                                        startingSelectedSlot = text
                                                        isFirstSelect = !isFirstSelect
                                                        startingTime(text)
                                                        Toast
                                                            .makeText(
                                                                context,
                                                                "Starting Time: $text ",
                                                                Toast.LENGTH_LONG
                                                            )
                                                            .show()
                                                    } else {
                                                        Toast
                                                            .makeText(
                                                                context,
                                                                "Starting Time must be less than Ending Time",
                                                                Toast.LENGTH_LONG
                                                            )
                                                            .show()
                                                    }
                                                }
                                            } else {
                                                if (
                                                    currentIndex in rangeStartIndex..rangeEndIndex &&
                                                    currentIndex > rangeStartIndex
                                                ) {
                                                    if (
                                                        text > startingSelectedSlot) {
                                                        endingSelectedSlot = text
                                                        isFirstSelect = !isFirstSelect
                                                        endingTime(text)
                                                        Toast
                                                            .makeText(
                                                                context,
                                                                "Ending Time: $text ",
                                                                Toast.LENGTH_LONG
                                                            )
                                                            .show()
                                                    } else {
                                                        Toast
                                                            .makeText(
                                                                context,
                                                                "Ending Time must be greater than Starting Time",
                                                                Toast.LENGTH_LONG
                                                            )
                                                            .show()
                                                    }

                                                }
                                            }
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewTimeSlot() {
    TimeSlotTable("07:00", "22:00", startingTime = {}, endingTime = {})
}
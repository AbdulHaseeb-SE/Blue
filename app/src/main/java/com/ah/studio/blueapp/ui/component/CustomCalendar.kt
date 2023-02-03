package com.ah.studio.blueapp.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.ui.theme.*
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.header.MonthState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle.SHORT
import java.util.*


@Composable
fun CustomCalendar(
    modifier: Modifier = Modifier,
    onClick: (LocalDate) -> Unit
) {

    BlueRoundedCornerShape(modifier = modifier) {
        SelectableCalendar(
            modifier = Modifier
                .animateContentSize()
                .padding(bottom = 30.dp),
            showAdjacentMonths = false,
            firstDayOfWeek = DayOfWeek.SUNDAY,
            dayContent = {
                DayContent(dayState = it) { selectedDate ->
                    onClick(selectedDate)
                }
            },
            weekHeader = { WeekHeader(daysOfWeek = it) },
            monthHeader = { MonthHeader(monthState = it) },
        )
    }
}

@Composable
fun MonthHeader(monthState: MonthState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 22.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = {
            monthState.currentMonth =
                monthState.currentMonth.minusMonths(1)
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                colorFilter = ColorFilter.tint(SeaBlue400),
                contentDescription = "Previous",
                modifier = Modifier
                    .rotate(180f)
            )
        }

        Text(
            "${monthState.currentMonth.month.name} " +
                    monthState.currentMonth.year.toString(),
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            fontSize = 17.sp

        )
        IconButton(onClick = {
            monthState.currentMonth =
                monthState.currentMonth.plusMonths(1)
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                colorFilter = ColorFilter.tint(SeaBlue400),
                contentDescription = "Next"
            )
        }
    }

}

@Composable
private fun WeekHeader(daysOfWeek: List<DayOfWeek>) {
    Row {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(SHORT, Locale.ROOT),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .padding(bottom = 10.dp)
            )
        }
    }
}

@Composable
private fun DayContent(dayState: DayState<DynamicSelectionState>, onClick: (LocalDate) -> Unit) {
    var currentDateColor by remember {
        mutableStateOf(Color.White)
    }

    var selectedDateColor by remember {
        mutableStateOf(Color.Black)
    }

    var backgroundColor by remember {
        mutableStateOf(SeaBlue08Percent)
    }

    var borderColor by remember {
        mutableStateOf(SeaBlue08Percent)
    }

    var selectedDate by remember {
        mutableStateOf(LocalDate.ofYearDay(dayState.date.year, dayState.date.dayOfYear))
    }
    currentDateColor = if (dayState.isCurrentDay) {
        Color.White
    } else {
        Color.Black
    }

    selectedDateColor = if (dayState.isCurrentDay) {
        Color.White
    } else {
        SeaBlue400
    }

    if (dayState.isCurrentDay) {
        backgroundColor = OxfordBlue900
        borderColor = SeaBlue400
    } else {
        backgroundColor = Color.Transparent
        borderColor = Color.Transparent
    }




    BlueRoundedCornerShape(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .padding(6.dp)
            .clickable {
                selectedDate = LocalDate.ofYearDay(dayState.date.year, dayState.date.dayOfYear)
                dayState.selectionState.onDateSelected(selectedDate)

                onClick(selectedDate)

                backgroundColor = if (
                    dayState.selectionState.isDateSelected(selectedDate)
                ) SeaBlue08Percent else Color.Transparent
                borderColor = if (
                    dayState.selectionState.isDateSelected(selectedDate)
                ) SeaBlue400 else Color.Transparent
            },
        borderColor = borderColor,
        containerColor = backgroundColor,
        shape = CircleShape
    ) {
        Text(
            text = dayState.date.dayOfMonth.toString(),
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = if (
                dayState.selectionState.isDateSelected(selectedDate)
            ) selectedDateColor else currentDateColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
        )
    }
}
package com.ah.studio.blueapp.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import java.time.DayOfWeek

@Composable
fun CustomCalendar(
    modifier: Modifier = Modifier
) {

    BlueRoundedCornerShape(modifier = modifier) {
        SelectableCalendar(
            modifier = Modifier.animateContentSize(),
            showAdjacentMonths = false,
            firstDayOfWeek = DayOfWeek.SUNDAY,/*
            monthContainer = { MonthContainer(it) },
            dayContent = { DayContent(dayState = it) },
            weekHeader = { WeekHeader(daysOfWeek = it) },
            monthHeader = { MonthHeader(monthState = it) },*/
        )
    }
}







/*Kalendar(
            modifier = modifier,
            kalendarType = KalendarType.Firey,
            kalendarThemeColor = KalendarThemeColor(
                backgroundColor = SeaBlue08Percent,
                dayBackgroundColor = Color.Transparent,
                headerTextColor = Color.Black
            ),
            kalendarDayColors = KalendarDayColors(
                textColor = Color.Black,
                selectedTextColor = Color.White
            )
        )*/
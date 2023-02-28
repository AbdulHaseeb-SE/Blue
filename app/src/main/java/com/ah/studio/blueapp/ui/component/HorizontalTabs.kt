package com.ah.studio.blueapp.ui.component

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.ui.screens.home.domain.dto.boatCategory.SubCategory
import com.ah.studio.blueapp.ui.theme.fontFamily

@Composable
fun HorizontalTabs(
    categoryList: List<SubCategory>?,
    backgroundColor: Color,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    indicatorActiveColor: Color,
    modifier: Modifier = Modifier,
    onSelected: (String) -> Unit
) {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    var indicatorActiveColorState by remember {
        mutableStateOf(unselectedContentColor)
    }

    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedIndex,
        backgroundColor = backgroundColor,
        contentColor = unselectedContentColor
    ) {
        categoryList?.forEachIndexed { index, item ->
            Tab(
                text = {
                    Text(
                        text = item.name,
                        fontSize = 14.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        color = if (index == selectedIndex) selectedContentColor else unselectedContentColor
                    )
                    indicatorActiveColorState =
                        if (index == selectedIndex) indicatorActiveColor else indicatorActiveColor
                },
                selected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                    onSelected(item.id)
                }
            )
        }
    }
}



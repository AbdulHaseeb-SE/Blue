package com.ah.studio.blueapp.ui.component

import android.widget.GridLayout.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ah.studio.blueapp.ui.theme.fontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    backgroundColor: Color,
    contentColor: Color,
    navigationIcon: Painter?,
    text: String,
    elevation: Dp,
    navigationIconContentDescription: String,
    actionIcons: @Composable() (RowScope.() -> Unit),
    onNavigationIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = backgroundColor,
            navigationIconContentColor = contentColor,
            actionIconContentColor = contentColor,
            titleContentColor = contentColor
        ),
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(onClick = { onNavigationIconClick() }) {

                    Icon(
                        modifier = modifier.size(24.dp),
                        painter = navigationIcon,
                        contentDescription = navigationIconContentDescription
                    )
                }
            }
        },
        title = {
            Text(
                text = text,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily,
            )
        },
        actions = actionIcons
    )
}
package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse
import com.ah.studio.blueapp.ui.theme.SeaBlue400
import com.ah.studio.blueapp.ui.theme.SeaBlue50
import com.ah.studio.blueapp.ui.theme.fontFamily

@Composable
fun BottomNavBar(
    items: List<BottomNavItemResponse>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItemResponse) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    BottomNavigation(
        backgroundColor = SeaBlue50,
        elevation = 0.dp,
    ) {
        var selectedIndex by remember {
            mutableStateOf(0)
        }

        items.forEachIndexed { index, item ->
            val currentDestination = item.route == navBackStackEntry?.destination?.route
            BottomNavigationItem(
                selected = currentDestination,
                onClick = {
                    onItemClick(item)
                    selectedIndex = index
                },
                selectedContentColor = SeaBlue400,
                unselectedContentColor = Color.Black,
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.name,
                            modifier = modifier
                                .height(20.dp)
                                .width(40.dp)
                                .padding(top = 4.dp)
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = item.name,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Justify,
                            fontFamily = fontFamily
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        if (selectedIndex == index) {
                            RoundedCornerLine(
                                width = 12.dp,
                                height = 4.dp,
                                color = SeaBlue400,
                                cornerRadius = 4.dp
                            )
                        }
                    }
                }
            )
        }
    }
}
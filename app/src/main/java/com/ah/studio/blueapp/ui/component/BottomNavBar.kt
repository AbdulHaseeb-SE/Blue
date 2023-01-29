package com.ah.studio.blueapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun BottomNavBar(
    items: List<BottomNavItemResponse>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItemResponse) -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier.padding(horizontal = 16.dp),
        backgroundColor = Color.White,
        elevation = 0.dp,
    ) {
        items.forEach { item ->
            val currentDestination = item.route == navBackStackEntry?.destination?.route
            BottomNavigationItem(
                selected = currentDestination,
                onClick = { onItemClick(item) },
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
                                .height(24.dp)
                                .width(48.dp)
                                .padding(top = 8.dp)
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Text(
                            text = item.name,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Justify
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                    }
                }
            )
        }
    }
}
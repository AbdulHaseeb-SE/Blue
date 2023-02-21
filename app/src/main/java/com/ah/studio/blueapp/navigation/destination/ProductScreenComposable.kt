package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.ProductScreen

fun NavGraphBuilder.productScreenComposable(
    navHostController: NavHostController
) {
    composable(
        route = ScreenController.Product.route
    ) {
        ProductScreen(
            onSubCategoryClick = { subCategoryId, subCategoryName ->
                navHostController.navigate(ScreenController.ProductList.route + "/$subCategoryId/$subCategoryName")
            },
            onSkipButtonClick = {
                navHostController.navigate(ScreenController.Payment.route)
            }
        )
    }
}
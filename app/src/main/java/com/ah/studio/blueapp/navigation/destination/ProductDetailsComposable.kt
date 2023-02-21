package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.ProductDetailsScreen

fun NavGraphBuilder.productDetailsComposable(navHostController: NavHostController) {
    composable(ScreenController.ProductDetails.route + "/{productId}",
        arguments = listOf(
            navArgument("productId") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { entry ->
        entry.arguments?.getString("productId").let { productId ->
            ProductDetailsScreen(
                productId = productId,
                onCartButtonClick = {
                    navHostController.navigate(ScreenController.Cart.route)
                },
                onViewCartClick = {
                    navHostController.navigate(ScreenController.Cart.route)
                },
            )
        }

    }
}
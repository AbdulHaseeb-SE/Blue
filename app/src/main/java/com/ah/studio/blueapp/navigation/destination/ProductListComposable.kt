package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.home.subScreens.ProductListScreen

fun NavGraphBuilder.productListComposable(
    navHostController: NavHostController
) {
    composable(
        route = ScreenController.ProductList.route + "/{subCategoryId}/{subCategoryName}",
        arguments = listOf(
            navArgument("subCategoryId") {
                type = NavType.StringType
                nullable = false
            },
            navArgument("subCategoryName") {
                type = NavType.StringType
                nullable = false
            }
        )
    ) { entry ->
        entry.arguments?.getString("subCategoryId").let { subCategoryId ->
            entry.arguments?.getString("subCategoryName").let { subCategoryName ->
                if (subCategoryId != null && subCategoryName != null) {
                    ProductListScreen(
                        subCategoryId = subCategoryId,
                        subCategoryName = subCategoryName,
                        onProductClick = { productId ->
                            navHostController.navigate(ScreenController.ProductDetails.route + "/$productId")
                        },
                        onSkipButtonClick = {
                            navHostController.navigate(ScreenController.Payment.route)
                        }
                    )
                }
            }
        }
    }
}
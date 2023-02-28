package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.account.AccountScreen
import com.ah.studio.blueapp.util.Graph

fun NavGraphBuilder.accountComposable(navHostController: NavHostController) {
    composable(
        route = ScreenController.Account.route
    ) {
        AccountScreen(
            onEditProfileClick = {
                navHostController.navigate(ScreenController.EditProfile.route)
            },
            onContactUsClick = {
                navHostController.navigate(ScreenController.ContactUs.route)
            },
            onTermConditionClick = {
                navHostController.navigate(Graph.AccountGraph)
            },
            onRefundPolicyClick = {
                navHostController.navigate(ScreenController.RefundPolicy.route)
            },
            onLogoutClick = {
                navHostController.popBackStack(Graph.Main, inclusive = false)
                navHostController.navigate(Graph.AUTHENTICATION)
            },
            onChangePasswordClick = {
                navHostController.navigate(ScreenController.ChangePassword.route)
            }
        )
    }
}
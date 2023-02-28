package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.account.subScreen.TermAndConditionScreen

fun NavGraphBuilder.termsConditionComposable(navHostController: NavHostController){
    composable(
        ScreenController.TermsCondition.route
    ){
        TermAndConditionScreen(
            onBackButtonClick = {
                navHostController.popBackStack()
            }
        )
    }
}
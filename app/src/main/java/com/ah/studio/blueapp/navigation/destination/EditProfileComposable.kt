package com.ah.studio.blueapp.navigation.destination

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.account.subScreen.EditProfileScreen

fun NavGraphBuilder.editProfileComposable(navHostController: NavHostController){
    composable(ScreenController.EditProfile.route){
        EditProfileScreen(
            onBackButtonClick = {
                navHostController.popBackStack()
            }
        )
    }
}
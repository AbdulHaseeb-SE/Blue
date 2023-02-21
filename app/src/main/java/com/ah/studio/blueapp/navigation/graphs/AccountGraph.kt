package com.ah.studio.blueapp.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.navigation.destination.*
import com.ah.studio.blueapp.util.Graph

fun NavGraphBuilder.accountNavGraph(navHostController: NavHostController) {
    navigation(
        route = Graph.AccountGraph,
        startDestination = ScreenController.TermsCondition.route
    ) {
        termsConditionComposable()
        refundPolicyComposable()
        changePasswordComposable(navHostController)
        editProfileComposable(navHostController)
        contactUsComposable(navHostController)
    }
}
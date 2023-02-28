package com.ah.studio.blueapp.ui.screens.main.domain.repository

import android.content.Context
import com.ah.studio.blueapp.R
import com.ah.studio.blueapp.SessionManager
import com.ah.studio.blueapp.navigation.ScreenController
import com.ah.studio.blueapp.ui.screens.main.domain.dto.BottomNavItemResponse

class BottomNavItemRepository(context: Context) : IBottomNavItemRepository {
    private val sessionManager = SessionManager(context)
    private val role = sessionManager.getRole()
    override fun getBottomNavItems(): MutableList<BottomNavItemResponse> =
        when (role) {
            "user" -> {
                mutableListOf(
                    BottomNavItemResponse(
                        name = "Home",
                        route = ScreenController.Home.route,
                        icon = R.drawable.ic_home
                    ),
                    BottomNavItemResponse(
                        name = "My Booking",
                        route = ScreenController.MyBooking.route,
                        icon = R.drawable.ic_ship
                    ),
                    BottomNavItemResponse(
                        name = "Seafarer",
                        route = ScreenController.Seafarer.route,
                        icon = R.drawable.ic_captian_cap
                    ),
                    BottomNavItemResponse(
                        name = "Account",
                        route = ScreenController.Account.route,
                        icon = R.drawable.ic_person
                    ),
                )
            }
            "vendor" -> {
                mutableListOf(
                    BottomNavItemResponse(
                        name = "Home",
                        route = ScreenController.Home.route,
                        icon = R.drawable.ic_home
                    ),
                    BottomNavItemResponse(
                        name = "My Booking",
                        route = ScreenController.MyBooking.route,
                        icon = R.drawable.ic_ship
                    ),
                    BottomNavItemResponse(
                        name = "My Parking",
                        route = ScreenController.MyParking.route,
                        icon = R.drawable.ic_ship
                    ),
                    BottomNavItemResponse(
                        name = "Seafarer",
                        route = ScreenController.Seafarer.route,
                        icon = R.drawable.ic_captian_cap
                    ),
                    BottomNavItemResponse(
                        name = "Account",
                        route = ScreenController.Account.route,
                        icon = R.drawable.ic_person
                    ),
                )
            }
            else -> {
                mutableListOf()
            }
        }
}